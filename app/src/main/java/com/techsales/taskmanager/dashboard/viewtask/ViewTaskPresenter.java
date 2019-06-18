package com.techsales.taskmanager.dashboard.viewtask;

import com.techsales.taskmanager.data.model.viewmodel.taskdetails.TaskDetailsViewModel;
import com.techsales.taskmanager.data.model.viewtask.TaskDetails;
import com.techsales.taskmanager.di.TaskManagerComponent;

class ViewTaskPresenter implements ViewTaskContract.Presenter {
    private TaskManagerComponent component;
    private ViewTaskContract.View view;

    ViewTaskPresenter(TaskManagerComponent component, ViewTaskContract.View view) {
        this.component = component;
        this.view = view;
    }

    @Override
    public TaskDetailsViewModel getTaskDetailsViewModel(TaskDetails taskDetails) {
        if (taskDetails != null) {
            return TaskDetails.mapToTaskDetailsViewModel(component.context(), taskDetails);
        }
        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
