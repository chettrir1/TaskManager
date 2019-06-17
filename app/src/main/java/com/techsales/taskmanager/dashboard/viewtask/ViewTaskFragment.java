package com.techsales.taskmanager.dashboard.viewtask;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.FragmentViewTaskBinding;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewTaskFragment extends BaseFragment {

    private static final String TASK_TITLE = "taskTitle";
    private static final String TASK_DESCRIPTION = "taskDescription";
    private static final String TASK_STATUS = "taskStatus";
    private static final String TASK_COLOR = "taskColor";
    private static final String TASK_ASSIGNED_DATE = "taskAssigned";
    private static final String TASK_DEATLINE = "taskDeadine";

    public static Fragment getInstance(String taskTitle, String taskDescription,
                                       String taskStatus, int taskColor,
                                       String taskAssigned, String taskDeadine) {
        ViewTaskFragment fragment = new ViewTaskFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TASK_TITLE, taskTitle);
        bundle.putString(TASK_DESCRIPTION, taskDescription);
        bundle.putString(TASK_STATUS, taskStatus);
        bundle.putInt(TASK_COLOR, taskColor);
        bundle.putString(TASK_ASSIGNED_DATE, taskAssigned);
        bundle.putString(TASK_DEATLINE, taskDeadine);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Inject
    Gson gson;

    private FragmentViewTaskBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_task, null, false);
        getDataWithBundle();

        return binding.getRoot();
    }

    private void getDataWithBundle() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            binding.tvTaskTitle.setText(bundle.getString(TASK_TITLE));
            binding.tvTaskDescription.setText(bundle.getString(TASK_DESCRIPTION));
            binding.tvTaskStatus.setText(bundle.getString(TASK_STATUS));
            binding.tvTaskStatus.setTextColor(bundle.getInt(TASK_COLOR));
            binding.includeDate.tvAssignedDate.setText(bundle.getString(TASK_ASSIGNED_DATE));
            binding.includeDate.tvDeadlineDate.setText(bundle.getString(TASK_DEATLINE));
        }
    }


}
