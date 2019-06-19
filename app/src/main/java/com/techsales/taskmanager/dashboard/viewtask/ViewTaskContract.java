package com.techsales.taskmanager.dashboard.viewtask;

import android.app.Activity;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;
import com.techsales.taskmanager.data.model.viewmodel.taskdetails.TaskDetailsViewModel;
import com.techsales.taskmanager.data.model.viewtask.TaskDetails;

public interface ViewTaskContract {
    interface View extends BaseView<Presenter> {

        void showProgress();

        void showFileUploadingError();

        void showFileUploadSuccess();

        void onPermisisonGranted();

        void onPermissionDenied();

        void openSetting();

    }

    interface Presenter extends BasePresenter {
        TaskDetailsViewModel getTaskDetailsViewModel(TaskDetails taskDetails);

        void requestPermission(Activity activity);

        void onPermissionResult(Activity activity, int requestCode,
                                String[] permissions, int[] grantResults);

        boolean isPermissionGranted();

/*
        void completeTask();
*/
    }
}
