package com.techsales.taskmanager.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import javax.inject.Inject;
import javax.inject.Singleton;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.techsales.taskmanager.utils.Constants.CAMERA_REQUEST_CODE;
import static com.techsales.taskmanager.utils.Constants.GALLERY_REQUEST_CODE;
import static com.techsales.taskmanager.utils.Constants.STORAGE_PERMISSION_CODE;

/**
 * Created by chettri on 1/23/18.
 */

public class Permission implements ActivityCompat.OnRequestPermissionsResultCallback {

    private final Activity activity;

    @Inject
    Permission(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (grantResults.length > 0) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (activity.shouldShowRequestPermissionRationale(CAMERA)) {
                            showMessageOkCancel(
                                    (dialogInterface, i) -> activity.requestPermissions(new String[]{CAMERA},
                                            CAMERA_REQUEST_CODE));
                            return;
                        }
                    }
                }
                break;

            case GALLERY_REQUEST_CODE:
                if (grantResults.length > 0) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (activity.shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)) {
                            showMessageOkCancel(
                                    (dialogInterface, i) -> activity.requestPermissions(new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE},
                                            GALLERY_REQUEST_CODE));
                            return;
                        }
                    }
                }
                break;

            case STORAGE_PERMISSION_CODE:
                if (grantResults.length > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (activity.shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) {
                            showMessageOkCancel((dialogInterface, i) -> activity.requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE},
                                    STORAGE_PERMISSION_CODE));
                            return;
                        }
                    }
                }
                break;

            default:
                break;
        }
    }

    private void showMessageOkCancel(DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(activity)
                .setMessage("")
                .setPositiveButton("ok", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    //Method for checking permission
    private boolean checkPermissionForCamera(Context context) {
        int camera = ContextCompat.checkSelfPermission(context, CAMERA);
        return camera == PackageManager.PERMISSION_GRANTED;
    }

    private boolean checkPermissionForGallery(Context context) {
        int hasReadPermission = ContextCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE);

        return hasReadPermission == PackageManager.PERMISSION_GRANTED;
    }

    private boolean checkPermissionForStorage(Context context) {
        int hasStoragePermsiion = ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE);
        return hasStoragePermsiion == PackageManager.PERMISSION_GRANTED;
    }

    //methods for requesting permission
    public void requestCameraPermission(Context context) {
        if (!checkPermissionForCamera(context)) {
            ActivityCompat.requestPermissions(activity, new String[]{
                    CAMERA}, CAMERA_REQUEST_CODE
            );
        }
    }

    public void requestGalleryPermission(Context context) {
        if (!checkPermissionForGallery(context)) {
            ActivityCompat.requestPermissions(activity, new String[]{
                    WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE
            }, GALLERY_REQUEST_CODE);
        }
    }

    public void requestStoragePermission(Context context) {
        if (!checkPermissionForStorage(context)) {
            ActivityCompat.requestPermissions(activity, new String[]{WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }
}
