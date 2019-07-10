package com.techsales.taskmanager.createtask.chooseemployee;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.techsales.taskmanager.BaseDialogFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.dashboard.container.DashboardActivity;
import com.techsales.taskmanager.data.model.createtask.AboutTask;
import com.techsales.taskmanager.data.model.viewmodel.chooseemployee.ChooseEmployeeViewModel;
import com.techsales.taskmanager.databinding.DialogChooseEmployeeBinding;
import com.techsales.taskmanager.utils.Commons;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.widget.LinearLayout.VERTICAL;

public class ChooseEmployeeFragment extends BaseDialogFragment implements ChooseEmployeeContract.View {
    private static final String ABOUT_TASK = "aboutTask";
    private DialogChooseEmployeeBinding binding;
    private List<String> employeeList;
    private ProgressDialog progressDialog;
    private boolean dividerPresent = false;

    @Inject
    ChooseEmployeeContract.Presenter presenter;

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
        getDataWithBundle();
        employeeList = new ArrayList<>(50);
        binding.contentState.setContent(binding.content);
        binding.tvDialogTitle.setText(R.string.dialog_choose_employee_title);

        binding.btnAssignTask.setOnClickListener(view -> {
            Log.v("employeeList", employeeList + "");
            Log.v("employeeListSize", employeeList.size() + "");
            Log.v("getJsonData", Commons.getJsonData(employeeList) + "");
            presenter.assignTask(Commons.getJsonData(employeeList));
        });

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
        if (!dividerPresent) {
            if (getContext() != null) {
                DividerItemDecoration dividerItemDecoration =
                        new DividerItemDecoration(getContext(), VERTICAL);
                binding.rvEmployeeList.addItemDecoration(dividerItemDecoration);
                dividerPresent = true;
            }
        }

        ChooseEmployeeRecyclerAdapter adapter = new ChooseEmployeeRecyclerAdapter(viewModels, presenter);
        binding.rvEmployeeList.setAdapter(adapter);
        binding.contentState.showContent();
    }

    @Override
    public void onEmployeeItemClick(ChooseEmployeeViewModel items, boolean isChecked) {
        if (!isChecked) {
            employeeList.add(items.getEmployeeId());
        } else {
            employeeList.remove(items.getEmployeeId());
        }
    }

    @Override
    public void showTaskAssignProgress() {
        progressDialog = Commons.showLoadingDialog(getContext());
    }

    @Override
    public void showTaskAssignError(String message) {
        Log.v("getMessage", message);
        dissmissDialog();
        if (getContext() != null)
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTaskAssignSuccess(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        dissmissDialog();
        dismiss();
        DashboardActivity.start(getActivity());

    }

    @Override
    public void showTaskAssignNetworkError() {
        if (getContext() != null)
            Commons.showSnackBar(getContext(), binding.llMainView, getContext().getString(R.string.error_network_not_available));
    }

    private void getDataWithBundle() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            AboutTask aboutTask = (AboutTask) bundle.getSerializable(ABOUT_TASK);
            presenter.getDataWithBundle(aboutTask);
        }
    }

    private void dissmissDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
