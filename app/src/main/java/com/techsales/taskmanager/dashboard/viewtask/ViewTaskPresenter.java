package com.techsales.taskmanager.dashboard.viewtask;

import android.net.Uri;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.error.FailedResponseException;
import com.techsales.taskmanager.data.error.NetworkNotAvailableException;
import com.techsales.taskmanager.data.model.viewmodel.taskdetails.TaskDetailsViewModel;
import com.techsales.taskmanager.data.model.viewtask.TaskDetails;
import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;

import java.io.File;

import io.reactivex.disposables.Disposable;
import okhttp3.MultipartBody;

class ViewTaskPresenter implements ViewTaskContract.Presenter {
    private final TaskManagerComponent component;
    private ViewTaskContract.View view;
    private Disposable disposable;

    ViewTaskPresenter(TaskManagerComponent component, ViewTaskContract.View view) {
        this.component = component;
        this.view = view;
    }

    @Override
    public TaskDetailsViewModel getTaskDetailsViewModel(TaskDetails taskDetails) {
        if (taskDetails != null) {
            return TaskDetails.mapToTaskDetailsViewModel(component.context(), taskDetails);
        }
        return null;
    }

    @Override
    public void onCameraClick() {
        if (!view.checkPermission()) {
            view.showPermissionDialog(false);
            return;
        }
        if (view.availableDisk() <= 5) {
            view.showNoSpaceDialog();
            return;
        }
        File file = view.newFile();
        if (file == null) {
            view.showErrorDialog();
            return;
        }
        view.startCamera(file);
    }

    @Override
    public void onGalleryClick() {
        if (!view.checkPermission()) {
            view.showPermissionDialog(true);
            return;
        }
        view.chooseGallery();
    }

    @Override
    public void showPreview(Uri mFileUri) {
        view.displayImagePreview(mFileUri);
    }

    @Override
    public void uploadAndComplete(String taskId, String status, String remarks, MultipartBody.Part part) {
       view.showProgress();
        disposable = component.data().uploadCompletedTask(taskId, status, remarks, part)
                .subscribe(baseResponse -> view.showUploadSuccess(), throwable -> {
                    if (throwable instanceof FailedResponseException)
                        view.showErrorUpload(throwable.getMessage());
                    else if (throwable instanceof NetworkNotAvailableException)
                        view.showNetworkNotAvailableError();
                    else
                        view.showErrorUpload(component.context().getString(R.string.error_server));
                });
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        Commons.dispose(disposable);
    }
}
