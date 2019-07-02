package com.techsales.taskmanager.createtask.chooseemployee;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.techsales.taskmanager.BaseDialogFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.DialogChooseEmployeeBinding;

import javax.inject.Inject;

public class ChooseEmployeeFragment extends BaseDialogFragment implements ChooseEmployeeContract.View {

    @Inject
    ChooseEmployeeContract.Presenter presenter;

    private DialogChooseEmployeeBinding binding;

    public static ChooseEmployeeFragment getInstance() {
        return new ChooseEmployeeFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        binding = DataBindingUtil.inflate(LayoutInflater.from(requireContext()), R.layout.dialog_choose_employee, null, false);
        binding.contentState.setContent(binding.content);

        binding.tvDialogTitle.setText(R.string.dialog_choose_employee_title);
        dialog.setContentView(binding.getRoot());
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onStop() {
        presenter.stop();
        super.onStop();
    }

    @Override
    public void showProgress() {
        binding.contentState.showProgress(getResources().getString(R.string.contacts_loading_message));

    }

    @Override
    public void showLoadingError(String message) {
        binding.contentState.showError(R.drawable.ic_loading_error, message);
    }

    @Override
    public void showNoNetworkAvailable() {
        binding.contentState.showError(R.drawable.no_internet, getString(R.string.error_network_not_available));
    }

    @Override
    public void showLoadingSuccess() {

    }
}
