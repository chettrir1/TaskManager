package com.techsales.taskmanager.data.model.viewmodel.notification;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.data.model.api.notification.NotificationResponse;
import com.techsales.taskmanager.utils.Commons;

public class NotificationViewModel extends BaseObservable {

    private NotificationResponse items;
    private Context context;

    public NotificationViewModel(Context context, NotificationResponse items) {
        this.items = items;
        this.context = context;
    }

    @Bindable
    public String getTimeAgo() {
        return Commons.getParsedDay(items.getCreatedAt());
    }

    @Bindable
    public String getNotificationTitle() {
        return items.getName();
    }

    @Bindable
    public String getNotificationDescription() {
        return items.getDescription();
    }


}
