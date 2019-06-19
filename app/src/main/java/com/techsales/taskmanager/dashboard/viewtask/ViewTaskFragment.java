package com.techsales.taskmanager.dashboard.viewtask;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.dashboard.viewtask.changestatus.ChangeStatusFragment;
import com.techsales.taskmanager.data.model.viewmodel.taskdetails.TaskDetailsViewModel;
import com.techsales.taskmanager.data.model.viewtask.TaskDetails;
import com.techsales.taskmanager.databinding.FragmentViewTaskBinding;
import com.techsales.taskmanager.utils.Commons;
import com.techsales.taskmanager.utils.fileupload.FileUtils;

import java.io.File;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;
import static com.techsales.taskmanager.utils.Constants.COUNT_ONE;
import static com.techsales.taskmanager.utils.Constants.COUNT_THREE;
import static com.techsales.taskmanager.utils.Constants.COUNT_TWO;

public class ViewTaskFragment extends BaseFragment implements ViewTaskContract.View {

    private static final String TASK_DETAILS = "taskDetails";
    private String taskId;
    private int taskStatus;
    private FragmentViewTaskBinding binding;
    private Uri imageUrl;

    @Inject
    ViewTaskContract.Presenter presenter;

    @Inject
    Context context;

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

        binding.includeStatus.tvStatusOpen.setOnClickListener(view -> {
            if (taskStatus == COUNT_ONE) {
                openBottomDialog(taskId, COUNT_TWO);
            } else {
                Commons.showSnackBar(component.context(), binding.llMainView, getResources()
                        .getString(R.string.error_task_status_changed));
            }
        });

        binding.includeStatus.tvStatusAppend.setOnClickListener(view -> {
            if (taskStatus == COUNT_TWO) {
                openBottomDialog(taskId, COUNT_THREE);
            } else {
                Commons.showSnackBar(component.context(), binding.llMainView, getResources()
                        .getString(R.string.error_task_status_changed));
            }
        });

        binding.includeCompleteTask.tvChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (presenter.isPermissionGranted()) {
                    afterPermissionGrant();
                }
                presenter.requestPermission(getActivity());
            }
        });

        return binding.getRoot();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showFileUploadingError() {

    }

    @Override
    public void showFileUploadSuccess() {

    }

    @Override
    public void onPermisisonGranted() {
        afterPermissionGrant();
    }

    @Override
    public void onPermissionDenied() {

    }

    @Override
    public void openSetting() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onPermissionResult(getActivity(), requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
                imageUrl = data.getData();
                File file = FileUtils.getFile(component.context(), imageUrl);
                binding.includeCompleteTask.tvChooseFile.setText(file.getName());
            }
        } catch (Exception ignored) {


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

    private void afterPermissionGrant() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 0);
    }
}
