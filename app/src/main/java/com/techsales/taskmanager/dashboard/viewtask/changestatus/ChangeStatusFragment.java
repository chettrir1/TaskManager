package com.techsales.taskmanager.dashboard.viewtask.changestatus;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.techsales.taskmanager.BaseDialogFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.FragmentChangeStatusBinding;
import com.techsales.taskmanager.utils.Commons;

import javax.inject.Inject;

import static com.techsales.taskmanager.utils.Constants.COUNT_ONE;
import static com.techsales.taskmanager.utils.Constants.COUNT_TWO;
import static com.techsales.taskmanager.utils.Constants.STATUS_NAME;
import static com.techsales.taskmanager.utils.Constants.STATUS_NEW;
import static com.techsales.taskmanager.utils.Constants.STATUS_OPEN;
import static com.techsales.taskmanager.utils.Constants.STATUS_PENDING;
import static com.techsales.taskmanager.utils.Constants.TASK_ID;


public class ChangeStatusFragment extends BaseDialogFragment implements ChangeStatusContract.View {
    @Inject
    ChangeStatusContract.Presenter presenter;
    private FragmentChangeStatusBinding binding;

    private String task_id;
    private int statusName;
    private ProgressDialog progressDialog;

    public static ChangeStatusFragment getInstance(String taskId, int statusName) {
        ChangeStatusFragment fragment = new ChangeStatusFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TASK_ID, taskId);
        bundle.putInt(STATUS_NAME, statusName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        binding = DataBindingUtil.inflate(LayoutInflater.from(requireContext()), R.layout.fragment_change_status, null, false);

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

        binding.tvDaiologDismiss.setOnClickListener(view -> dismiss());

        dialog.setContentView(binding.getRoot());
        return dialog;
    }

    @Override
    public void showProgress() {
        progressDialog = Commons.showLoadingDialog(getContext());
    }

    @Override
    public void showChangeStatusError(String message) {
        dismissDialog();
        Commons.showSnackBar(component.context(), binding.llChangeStatus, message);
    }

    @Override
    public void showNetworkNotAvailableError() {
        dismissDialog();
        Commons.showSnackBar(component.context(), binding.llChangeStatus, component.context().getResources().getString(R.string.error_network_not_available));
    }

    @Override
    public void showChangeStatusSuccess(String message) {
        dismissDialog();
        Toast.makeText(component.context(), message, Toast.LENGTH_SHORT).show();
        this.dismiss();
        if (getActivity() != null)
            getActivity().onBackPressed();
    }

    private String getStatusCount() {
        if (statusName == COUNT_ONE) {
            return STATUS_OPEN;
        }

        if (statusName == COUNT_TWO) {
            return STATUS_PENDING;
        }
        return STATUS_NEW;
    }

    private void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

}
