package com.techsales.taskmanager.data.model.viewmodel.notification;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.data.model.api.notification.NotificationResponse;

public class NotificationViewModel extends BaseObservable {

    private NotificationResponse notificationResponse;
    private Context context;

    public NotificationViewModel(Context context, NotificationResponse notificationResponse) {
        this.notificationResponse = notificationResponse;
        this.context = context;
    }

    @Bindable
    public String getTimeAgo() {
        return notificationResponse.getCreatedAt();
    }

    @Bindable
    public String getNotificationTitle() {
        return notificationResponse.getName();
    }

    @Bindable
    public String getNotificationDescription() {
        return notificationResponse.getDescription();
    }


}
