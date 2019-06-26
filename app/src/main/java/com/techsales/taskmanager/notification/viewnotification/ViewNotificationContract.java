package com.techsales.taskmanager.notification.viewnotification;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;
import com.techsales.taskmanager.data.model.notification.NotificationDetails;
import com.techsales.taskmanager.data.model.viewmodel.notification.NotificationDetailsViewModel;

public interface ViewNotificationContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        NotificationDetailsViewModel getNotificationDetailsViewModel(NotificationDetails notificationDetails);
    }
}
