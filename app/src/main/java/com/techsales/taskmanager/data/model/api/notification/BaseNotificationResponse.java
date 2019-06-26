package com.techsales.taskmanager.data.model.api.notification;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.techsales.taskmanager.data.model.viewmodel.notification.NotificationViewModel;

import java.util.ArrayList;
import java.util.List;

public class BaseNotificationResponse {

    @SerializedName("data")
    private List<NotificationResponse> notificationResponses;

    public List<NotificationResponse> getNotificationResponses() {
        return notificationResponses;
    }

    public void setNotificationResponses(List<NotificationResponse> notificationResponses) {
        this.notificationResponses = notificationResponses;
    }

    public static List<NotificationViewModel> mapToViewModel(Context context, List<NotificationResponse> items) {
        final int count = items.size();
        ArrayList<NotificationViewModel> viewModels = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            viewModels.add(new NotificationViewModel(context, items.get(i)));
        }
        return viewModels;
    }
}
