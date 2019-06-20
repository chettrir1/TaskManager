package com.techsales.taskmanager.dashboard.viewtask;

import android.app.Activity;
import android.net.Uri;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;
import com.techsales.taskmanager.data.model.viewmodel.taskdetails.TaskDetailsViewModel;
import com.techsales.taskmanager.data.model.viewtask.TaskDetails;

import java.io.File;

import okhttp3.MultipartBody;

public interface ViewTaskContract {
    interface View extends BaseView<Presenter> {

        boolean checkPermission();

        void showPermissionDialog();

        File getFilePath();

        void openSetting();

        void startCamera(File file);

        void chooseGallery();

        void showNoSpaceDialog();

        int availableDisk();

        File newFile();

        void showErrorDialog();

        void displayImagePreview(Uri mFileUri);

        void showProgress();

        void showErrorUpload(String message);

        void showNetworkNotAvailableError();

        void showUploadSuccess();
    }

    interface Presenter extends BasePresenter {
        TaskDetailsViewModel getTaskDetailsViewModel(TaskDetails taskDetails);

        void onCameraClick();

        void onGalleryClick();

        void permissionDenied();

        void showPreview(Uri mFileUri);

        void uploadAndComplete(String taskId, String status, String remarks, MultipartBody.Part part);
    }

}
