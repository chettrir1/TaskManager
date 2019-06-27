package com.techsales.taskmanager.status;

import com.techsales.taskmanager.di.TaskManagerComponent;

class StatusPresenter implements StatusContract.Presenter {
    private final TaskManagerComponent component;
    private StatusContract.View view;

    StatusPresenter(TaskManagerComponent component, StatusContract.View view) {
        this.component = component;
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
