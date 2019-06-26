package com.techsales.taskmanager.notification.viewnotification;

import com.techsales.taskmanager.data.model.notification.NotificationDetails;
import com.techsales.taskmanager.data.model.viewmodel.notification.NotificationDetailsViewModel;
import com.techsales.taskmanager.di.TaskManagerComponent;

class ViewNotificationPresenter implements ViewNotificationContract.Presenter {
    private final TaskManagerComponent component;
    private ViewNotificationContract.View view;

    ViewNotificationPresenter(TaskManagerComponent component, ViewNotificationContract.View view) {
        this.component = component;
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public NotificationDetailsViewModel getNotificationDetailsViewModel(NotificationDetails notificationDetails) {
        if (notificationDetails != null) {
            return NotificationDetails.mapToNotificationDetailsViewModel(component.context(), notificationDetails);
        }
        return null;
    }
}
