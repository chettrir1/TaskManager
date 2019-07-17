package com.techsales.taskmanager.data.model.notification;

import com.techsales.taskmanager.data.model.viewmodel.notification.NotificationDetailsViewModel;

import java.io.Serializable;

public class NotificationDetails implements Serializable {
    private String time;
    private String notificationTitle;
    private String notificationDescription;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationDescription() {
        return notificationDescription;
    }

    public void setNotificationDescription(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }

    public static NotificationDetailsViewModel mapToNotificationDetailsViewModel(NotificationDetails notificationDetails) {
        return new NotificationDetailsViewModel(notificationDetails);
    }


}
