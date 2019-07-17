package com.techsales.taskmanager.data.model.viewmodel.notification;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.data.model.notification.NotificationDetails;
import com.techsales.taskmanager.utils.Commons;

public class NotificationDetailsViewModel extends BaseObservable {

    private NotificationDetails notificationDetails;

    public NotificationDetailsViewModel(NotificationDetails notificationDetails) {
        this.notificationDetails = notificationDetails;
    }

    @Bindable
    public String getNotificationTitle() {
        return notificationDetails.getNotificationTitle();
    }

    @Bindable
    public String getNotificationDescription() {
        return notificationDetails.getNotificationDescription();
    }

    @Bindable
    public String getNotificationDate() {
        return Commons.dateParse(notificationDetails.getTime());
    }
}
