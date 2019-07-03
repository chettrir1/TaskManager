package com.techsales.taskmanager.createtask.chooseemployee;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.techsales.taskmanager.BaseDialogFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.createtask.AboutTask;
import com.techsales.taskmanager.data.model.viewmodel.chooseemployee.ChooseEmployeeViewModel;
import com.techsales.taskmanager.databinding.DialogChooseEmployeeBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.widget.LinearLayout.VERTICAL;

public class ChooseEmployeeFragment extends BaseDialogFragment implements ChooseEmployeeContract.View {
    private static final String ABOUT_TASK = "aboutTask";
    private DialogChooseEmployeeBinding binding;
    private List<String> employeeList = new ArrayList<>();

    @Inject
    ChooseEmployeeContract.Presenter presenter;
    private boolean isChecked = false;

    public static ChooseEmployeeFragment getInstance(AboutTask tasks) {
        ChooseEmployeeFragment fragment = new ChooseEmployeeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ABOUT_TASK, tasks);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        binding = DataBindingUtil.inflate(LayoutInflater.from(requireContext()), R.layout.dialog_choose_employee, null, false);
        binding.contentState.setContent(binding.content);
        binding.tvDialogTitle.setText(R.string.dialog_choose_employee_title);
        binding.btnAssignTask.setOnClickListener(view -> Log.v("employeeList", employeeList.size() + ""));
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
    public void showLoadingSuccess(List<ChooseEmployeeViewModel> viewModels) {
        if (getContext() != null) {
            DividerItemDecoration dividerItemDecoration =
                    new DividerItemDecoration(getContext(), VERTICAL);
            binding.rvEmployeeList.addItemDecoration(dividerItemDecoration);
        }

        ChooseEmployeeRecyclerAdapter adapter = new ChooseEmployeeRecyclerAdapter(viewModels, presenter);
        binding.rvEmployeeList.setAdapter(adapter);
        binding.contentState.showContent();
    }

    @Override
    public void onEmployeeItemClick(ChooseEmployeeViewModel items, int position, CheckBox cbAssignTo) {
        if (!isChecked) {
            Toast.makeText(getContext(), "added" + items.getEmployeeName(), Toast.LENGTH_SHORT).show();
            employeeList.add(items.getEmployeeName());
            cbAssignTo.setChecked(true);
            isChecked = true;
        } else {
            Toast.makeText(getContext(), "removed" + items.getEmployeeName(), Toast.LENGTH_SHORT).show();
            employeeList.remove(items.getEmployeeName());
            cbAssignTo.setChecked(false);
            isChecked = false;
        }
    }
}
