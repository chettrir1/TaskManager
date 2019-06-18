package com.techsales.taskmanager.dashboard.viewtask;


import android.os.Bundle;
import android.util.Log;
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

import javax.inject.Inject;

public class ViewTaskFragment extends BaseFragment implements ViewTaskContract.View {

    private static final String TASK_DETAILS = "taskDetails";
    private String task_id;

    public static Fragment getInstance(TaskDetails details) {
        ViewTaskFragment fragment = new ViewTaskFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TASK_DETAILS, details);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Inject
    ViewTaskContract.Presenter presenter;

    private FragmentViewTaskBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_task, null, false);
        getDataWithBundle();

        binding.includeStatus.tvStatusOpen.setOnClickListener(view -> openBottomDialog(task_id, 1));

        binding.includeStatus.tvStatusAppend.setOnClickListener(view -> openBottomDialog(task_id, 2));

        return binding.getRoot();
    }

    private void getDataWithBundle() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            TaskDetails taskDetails = (TaskDetails) bundle.getSerializable(TASK_DETAILS);
            if (taskDetails != null) {
                task_id = String.valueOf(taskDetails.getTaskId());
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

}
