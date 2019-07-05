package com.techsales.taskmanager.createtask;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.createtask.chooseemployee.ChooseEmployeeFragment;
import com.techsales.taskmanager.data.model.createtask.AboutTask;
import com.techsales.taskmanager.databinding.FragmentCreateNewTaskBinding;

import java.util.List;

import javax.inject.Inject;

public class CreateNewTaskFragment extends BaseFragment implements CreateNewTaskContract.View {
    private FragmentCreateNewTaskBinding binding;

    @Inject
    CreateNewTaskContract.Presenter presenter;

    public static Fragment getInstance() {
        return new CreateNewTaskFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_new_task, null, false);
        binding.btnAddTask.setOnClickListener(view ->
                presenter.validateNewTask(
                        binding.etTaskTitle.getText().toString(),
                        binding.prioritySpinner.getSelectedItem().toString(),
                        binding.etClientName.getText().toString(),
                        binding.etTaskDeadline.getText().toString(),
                        binding.etClientPhone.getText().toString(),
                        binding.etTaskDetails.getText().toString()
                ));

        return binding.getRoot();
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
    public void setPrioritySpinner(List<String> priority) {
        if (getContext() != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, priority);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.prioritySpinner.setAdapter(adapter);
        }
    }

    @Override
    public void setTaskTypeSpinner(List<String> type) {
        if (getContext() != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, type);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.taskTypeSpinner.setAdapter(adapter);
        }
    }

    @Override
    public void errorEmptyTitleVisible() {
        binding.etTaskTitle.requestFocus();
        binding.tvTitleError.setVisibility(View.VISIBLE);
    }

    @Override
    public void errorEmptyDeadlineVisible() {
        binding.etTaskDeadline.requestFocus();
        binding.tvDeadlineError.setVisibility(View.VISIBLE);
    }

    @Override
    public void errorClientNameVisible() {
        binding.etClientName.requestFocus();
        binding.tvClientNameError.setVisibility(View.VISIBLE);
    }

    @Override
    public void errorEmptyTitleInvisible() {
        binding.tvTitleError.setVisibility(View.GONE);
    }

    @Override
    public void errorEmptyDeadlineInvisible() {
        binding.tvDeadlineError.setVisibility(View.GONE);
    }

    @Override
    public void errorClientNameInvisible() {
        binding.tvClientNameError.setVisibility(View.GONE);
    }

    @Override
    public void validationSucces(AboutTask tasks) {
        if (getActivity() != null) {
            ChooseEmployeeFragment fragment = ChooseEmployeeFragment.getInstance(tasks);
            fragment.show(getActivity().getSupportFragmentManager(), "dialog");
            fragment.setCancelable(true);
        }
    }
}
