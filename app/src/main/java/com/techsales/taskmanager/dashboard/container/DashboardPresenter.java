package com.techsales.taskmanager.dashboard.container;

import com.techsales.taskmanager.di.TaskManagerComponent;

public class DashboardPresenter implements DashboardContract.Presenter {

    private final TaskManagerComponent component;
    private DashboardContract.View view;

    DashboardPresenter(TaskManagerComponent component, DashboardContract.View view) {
        this.component = component;
        this.view = view;
    }

    @Override
    public void start() {
        String type = component.data().savedUserInfo().getUserType();
        if (type.equals("1")) {
            view.setToolbarTitle("Administrator");
        } else {
            view.setToolbarTitle("Office Staff");
        }
    }

    @Override
    public void stop() {

    }
}
