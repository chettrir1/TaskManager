package com.techsales.taskmanager.dashboard.viewtask.changestatus;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.techsales.taskmanager.BaseDialog;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.FragmentChangeStatusBinding;
import com.techsales.taskmanager.utils.Commons;

import javax.inject.Inject;


public class ChangeStatusFragment extends BaseDialog implements ChangeStatusContract.View {
    private static final String STATUS_NAME = "statusName";
    private static final String TASK_ID = "taskId";
    private static final String STATUS_NEW = "NEW TASK";
    private static final String STATUS_OPEN = "OPEN TASK";
    private static final String STATUS_PENDING = "PENDING TASK";
    private String task_id;
    private int statusName;

    public static ChangeStatusFragment getInstance(String taskId, int statusName) {
        ChangeStatusFragment fragment = new ChangeStatusFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TASK_ID, taskId);
        bundle.putInt(STATUS_NAME, statusName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Inject
    ChangeStatusContract.Presenter presenter;

    private FragmentChangeStatusBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_status, null, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            task_id = bundle.getString(TASK_ID);
            statusName = bundle.getInt(STATUS_NAME);
        }
        binding.tvStatusName.setText(getStatusCount());
        binding.tvStatusName.setOnClickListener(view -> {
            String remarks = binding.etRemarks.getText().toString();
            presenter.changeStatus(task_id, String.valueOf(statusName), remarks);
        });
        return binding.getRoot();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showChangeStatusError(String message) {
        Commons.showSnackBar(component.context(), binding.llChangeStatus, message);
    }

    @Override
    public void showNetworkNotAvailableError() {
        Commons.showSnackBar(component.context(), binding.llChangeStatus, component.context().getResources().getString(R.string.network_not_available_error));
    }

    @Override
    public void showChangeStatusSuccess(String message) {
        Toast.makeText(component.context(), message, Toast.LENGTH_SHORT).show();
        this.dismiss();
    }

    private String getStatusCount() {
        if (statusName == 1) {
            return STATUS_OPEN;
        }

        if (statusName == 2) {
            return STATUS_PENDING;
        }
        return STATUS_NEW;
    }
}
