package com.techsales.taskmanager.dashboard.viewtask;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.techsales.taskmanager.data.model.viewmodel.taskdetails.TaskDetailsViewModel;
import com.techsales.taskmanager.data.model.viewtask.TaskDetails;
import com.techsales.taskmanager.di.TaskManagerComponent;

class ViewTaskPresenter implements ViewTaskContract.Presenter {
    private final TaskManagerComponent component;
    private ViewTaskContract.View view;
    private static final int EXTERNAL_STORAGE_CODE = 100;

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
    public void requestPermission(Activity activity) {
        ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA);
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, EXTERNAL_STORAGE_CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onPermissionResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == EXTERNAL_STORAGE_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                view.onPermisisonGranted();
            } else {
                view.onPermissionDenied();
                boolean showRationale = activity.shouldShowRequestPermissionRationale
                        (Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (!showRationale) {
                    view.openSetting();
                }
            }
        }
    }

    @Override
    public boolean isPermissionGranted() {
        int result = ContextCompat.checkSelfPermission(component.context(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
