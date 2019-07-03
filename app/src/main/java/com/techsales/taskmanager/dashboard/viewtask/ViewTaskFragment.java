package com.techsales.taskmanager.dashboard.viewtask;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.BuildConfig;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.dashboard.viewtask.changestatus.ChangeStatusFragment;
import com.techsales.taskmanager.data.model.viewmodel.taskdetails.TaskDetailsViewModel;
import com.techsales.taskmanager.data.model.viewtask.TaskDetails;
import com.techsales.taskmanager.databinding.FragmentViewTaskBinding;
import com.techsales.taskmanager.utils.Commons;
import com.techsales.taskmanager.utils.fileupload.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.techsales.taskmanager.utils.Constants.COUNT_THREE;
import static com.techsales.taskmanager.utils.Constants.COUNT_ZERO;
import static com.techsales.taskmanager.utils.Constants.COUNT_TWO;
import static com.techsales.taskmanager.utils.Constants.COUNT_ONE;

public class ViewTaskFragment extends BaseFragment implements ViewTaskContract.View {

    private static final String TASK_DETAILS = "taskDetails";
    private String taskId;
    private int taskStatus;
    private FragmentViewTaskBinding binding;
    private Uri imageUri;

    private static final long NEEDED_SPACE = 1048576;
    private static final int REQUEST_TAKE_PHOTO = 101;
    private static final int REQUEST_GALLERY_PHOTO = 102;
    private static final String TAKE_PHOTO = "Take photo";
    private static final String CHOOSE_FROM_GALLERY = "Choose from gallery";
    private static final String DIALOG_CANCEL = "Dismiss";

    private static String[] permissions = new String[]{
            Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private File image_file;

    @Inject
    ViewTaskContract.Presenter presenter;

    public static Fragment getInstance(TaskDetails details) {
        ViewTaskFragment fragment = new ViewTaskFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TASK_DETAILS, details);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_task, null, false);
        getDataWithBundle();

        if (taskStatus == COUNT_TWO) {
            setCompleteTaskVisible();
        } else {
            setChangeStatusVisible();
        }

        binding.includeStatus.tvStatusOpen.setOnClickListener(view -> {
            if (taskStatus == COUNT_ZERO) {
                openBottomDialog(taskId, COUNT_ONE);
            } else {
                Commons.showSnackBar(component.context(), binding.llMainView, getResources()
                        .getString(R.string.view_task_error_status_cant_change));
            }
        });

        binding.includeStatus.tvStatusAppend.setOnClickListener(view -> {
            if (taskStatus == COUNT_ONE) {
                openBottomDialog(taskId, COUNT_TWO);
            } else {
                Commons.showSnackBar(component.context(), binding.llMainView, getResources()
                        .getString(R.string.view_task_error_status_cant_change));
            }
        });

        binding.includeCompleteTask.tvChooseFile.setOnClickListener(view -> selectImage());

        binding.includeCompleteTask.tvCompleteAndUpload.setOnClickListener(view -> uploadAndComplete());

        return binding.getRoot();
    }

    private void setChangeStatusVisible() {
        binding.includeStatus.llChangeStatus.setVisibility(View.VISIBLE);
        binding.includeCompleteTask.llChooseFile.setVisibility(View.GONE);
    }

    private void setCompleteTaskVisible() {
        binding.includeStatus.llChangeStatus.setVisibility(View.GONE);
        binding.includeCompleteTask.llChooseFile.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {
                presenter.showPreview(imageUri);

            } else if (requestCode == REQUEST_GALLERY_PHOTO) {
                if (data != null) {
                    imageUri = data.getData();
                    presenter.showPreview(imageUri);
                }
            }
        }
    }

    @Override
    public boolean checkPermission() {
        for (String mPermission : permissions) {
            int result = ActivityCompat.checkSelfPermission(component.context(), mPermission);
            if (result == PackageManager.PERMISSION_DENIED)
                return false;
        }
        return true;
    }

    @Override
    public void showPermissionDialog() {
        Dexter.withActivity(getActivity()).withPermissions(permissions)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        report.areAllPermissionsGranted();
                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingDailog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).withErrorListener(error -> showErrorDialog())
                .onSameThread()
                .check();
    }

    @Override
    public File getFilePath() {
        if (getActivity() != null) {
            return getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        }
        return null;
    }

    @Override
    public void openSetting() {
        if (getActivity() != null) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
            intent.setData(uri);
            startActivityForResult(intent, REQUEST_TAKE_PHOTO);
        }
    }

    @Override
    public void startCamera(File file) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (getActivity() != null)
            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                if (file != null) {
                    image_file = file;
                    imageUri = FileProvider.getUriForFile(component.context(),
                            BuildConfig.APPLICATION_ID + ".provider", file);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                }
            }
    }

    @Override
    public void chooseGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickPhoto.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(pickPhoto, REQUEST_GALLERY_PHOTO);
    }

    @Override
    public void showNoSpaceDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(component.context());
        builder.setTitle(getString(R.string.view_task_error_storage_full));
        builder.setMessage(getString(R.string.view_task_error_isufficent_memory));
        builder.setPositiveButton(getString(R.string.view_task_dialog_dimiss), (dialog, which) -> dialog.cancel());
        builder.show();
    }

    @Override
    public int availableDisk() {
        File filePath = getFilePath();
        long freeSpace = filePath.getFreeSpace();
        return Math.round(freeSpace / NEEDED_SPACE);
    }

    @Override
    public File newFile() {
        Calendar calendar = Calendar.getInstance();
        long timeInMillis = calendar.getTimeInMillis();
        String fileName = timeInMillis + ".jpeg";
        File filePath = getFilePath();
        try {
            File newFile = new File(filePath.getAbsoluteFile(), fileName);
            newFile.createNewFile();
            return newFile;
        } catch (IOException ignored) {

        }
        return null;
    }

    @Override
    public void showErrorDialog() {
        Toast.makeText(component.context(), getString(R.string.view_task_error_empty_task), Toast.LENGTH_SHORT).show();
    }


    @SuppressLint("CheckResult")
    @Override
    public void displayImagePreview(Uri mFileUri) {
        Glide.with(this).load(mFileUri).apply(new RequestOptions()
                .fitCenter()).format(DecodeFormat.PREFER_ARGB_8888)
                .override(Target.SIZE_ORIGINAL)
                .into(binding.includeCompleteTask.ivChoosedImage);
    }

    @Override
    public void showProgress() {
        binding.includeCompleteTask.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorUpload(String message) {
        hideProgressBar();
        Commons.showSnackBar(component.context(), binding.llMainView, message);
    }

    @Override
    public void showNetworkNotAvailableError() {
        hideProgressBar();
        Commons.showSnackBar(component.context(), binding.llMainView,
                getString(R.string.error_network_not_available));

    }

    @Override
    public void showUploadSuccess() {
        hideProgressBar();
        Toast.makeText(component.context(), R.string.view_task_success_file_upload, Toast.LENGTH_SHORT).show();
        if (getActivity() != null)
            getActivity().onBackPressed();
    }

    private void selectImage() {
        final CharSequence[] items = {getString(R.string.view_task_text_take_photo), getString(R.string.view_task_text_gallery),
                getString(R.string.view_task_text_dialog_dismiss)};
        if (getActivity() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setItems(items, (dialog, item) -> {
                if (items[item].equals(TAKE_PHOTO)) {
                    presenter.onCameraClick();
                } else if (items[item].equals(CHOOSE_FROM_GALLERY)) {
                    presenter.onGalleryClick();
                } else if (items[item].equals(DIALOG_CANCEL)) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
    }

    private void getDataWithBundle() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            TaskDetails taskDetails = (TaskDetails) bundle.getSerializable(TASK_DETAILS);
            if (taskDetails != null) {
                taskId = String.valueOf(taskDetails.getTaskId());
                taskStatus = taskDetails.getTaskStatus();
                TaskDetailsViewModel taskDetailsViewModel = presenter.getTaskDetailsViewModel(taskDetails);
                if (taskDetailsViewModel != null) {
                    binding.setTaskDetails(taskDetailsViewModel);
                }
            }
        }
    }

    private void openBottomDialog(String id, int statusCount) {
        ChangeStatusFragment fragment = ChangeStatusFragment.getInstance(id, statusCount);
        if (getActivity() != null)
            fragment.show(getActivity().getSupportFragmentManager(), "dialog");
        fragment.setCancelable(false);
    }

    private void showSettingDailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(component.context());
        builder.setTitle(getResources().getString(R.string.view_task_text_need_permissions));
        builder.setMessage(getResources().getString(R.string.view_task_text_permission_message));
        builder.setPositiveButton(getResources().getString(R.string.view_task_go_to_settings), (dialog, which) -> {
            dialog.cancel();
            openSetting();
        });
        builder.setNegativeButton(getResources().getString(R.string.view_task_error_empty_task), (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void uploadAndComplete() {
        File file;
        if (image_file != null) {
            file = image_file;
        } else {
            file = FileUtils.getFile(component.context(), imageUri);
        }

        if (file != null && imageUri != null) {
            if (getActivity() != null) {
                RequestBody file_part = RequestBody.create(MediaType.parse(Objects.requireNonNull(getActivity().getContentResolver().getType(imageUri))),
                        file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("file_upload", file.getName(), file_part);
                String remarks = binding.includeCompleteTask.etRemarks.getText().toString();
                presenter.uploadAndComplete(taskId, String.valueOf(COUNT_THREE), remarks, part);
            }
        }
    }

    private void hideProgressBar() {
        if (binding.includeCompleteTask.progressBar != null) {
            binding.includeCompleteTask.progressBar.setVisibility(View.GONE);
        }
    }
}
