package com.techsales.taskmanager.createtask;

import android.text.TextUtils;
import android.util.Log;

import com.techsales.taskmanager.data.model.createtask.AboutTask;
import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Constants;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void validateNewTask(String taskTitle, String taskPriority, String clientName,
                                String taskDeadline, String clientPhone, String taskDescription) {

        if (TextUtils.isEmpty(taskTitle))
            view.errorEmptyTitleVisible();
        else if (TextUtils.isEmpty(taskDeadline))
            view.errorEmptyDeadlineVisible();
        else if (TextUtils.isEmpty(clientName))
            view.errorClientNameVisible();
        else {
            view.errorEmptyTitleInvisible();
            view.errorEmptyDeadlineInvisible();
            view.errorClientNameInvisible();
            AboutTask aboutTask = new AboutTask();
            aboutTask.setTitle(taskTitle);
            aboutTask.setDeadline(taskDeadline);
            aboutTask.setTaskPriority(getPriority(taskPriority));
            aboutTask.setClientName(clientName);
            aboutTask.setClientPhone(clientPhone);
            aboutTask.setTaskDetails(taskDescription);
            view.validationSucces(aboutTask);
        }
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

    private int getPriority(String priority) {
        if (priority.equals(Constants.PRIORITY_TYPE_ONE))
            return Constants.COUNT_ONE;

        if (priority.equals(Constants.PRIORITY_TYPE_TWO))
            return Constants.COUNT_TWO;

        if (priority.equals(Constants.PRIORITY_TYPE_THREE))
            return Constants.COUNT_THREE;

        return Constants.COUNT_ONE;
    }
}
