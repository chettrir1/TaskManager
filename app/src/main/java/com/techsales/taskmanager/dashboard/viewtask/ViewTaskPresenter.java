package com.techsales.taskmanager.dashboard.viewtask;

import android.net.Uri;

import com.techsales.taskmanager.data.model.viewmodel.taskdetails.TaskDetailsViewModel;
import com.techsales.taskmanager.data.model.viewtask.TaskDetails;
import com.techsales.taskmanager.di.TaskManagerComponent;

import java.io.File;

class ViewTaskPresenter implements ViewTaskContract.Presenter {
    private final TaskManagerComponent component;
    private ViewTaskContract.View view;

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
            view.showPermissionDialog();
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
            view.showPermissionDialog();
            return;
        }
        view.chooseGallery();
    }

    @Override
    public void saveImage(Uri uri) {

    }

    @Override
    public void permissionDenied() {
        view.showPermissionDialog();
    }

    @Override
    public void showPreview(String mFilePath) {
        view.displayImagePreview(mFilePath);
    }

    @Override
    public void showPreview(Uri mFileUri) {
        view.displayImagePreview(mFileUri);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
