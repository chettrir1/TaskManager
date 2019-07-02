package com.techsales.taskmanager.createtask;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;
import com.techsales.taskmanager.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class CreateNewTaskPresenter implements CreateNewTaskContract.Presenter {

    private final TaskManagerComponent component;
    private CreateNewTaskContract.View view;

    CreateNewTaskPresenter(TaskManagerComponent component, CreateNewTaskContract.View view) {
        this.component = component;
        this.view = view;
    }

    @Override
    public void start() {
        setTaskPriority();
        setTaskType();
    }

    @Override
    public void stop() {
    }

    private void setTaskPriority() {
        List<String> priorities = new ArrayList<>();
        priorities.add(Constants.PRIORITY_TYPE_ONE);
        priorities.add(Constants.PRIORITY_TYPE_TWO);
        priorities.add(Constants.PRIORITY_TYPE_THREE);
        view.setPrioritySpinner(priorities);
    }

    private void setTaskType() {
        List<String> type = new ArrayList<>();
        type.add(Constants.TASK_TYPE_NEW);
        type.add(Constants.TASK_TYPE_MANTAIN);
        view.setTaskTypeSpinner(type);
    }
}
