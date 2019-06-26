package com.techsales.taskmanager.data.model.viewmodel.notification;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.data.model.notification.NotificationDetails;

public class NotificationDetailsViewModel extends BaseObservable {

    private NotificationDetails notificationDetails;
    private Context context;

    public NotificationDetailsViewModel(Context context, NotificationDetails notificationDetails) {
        this.notificationDetails = notificationDetails;
        this.context = context;
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
        return notificationDetails.getTime();
    }
}
