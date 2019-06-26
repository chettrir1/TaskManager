package com.techsales.taskmanager.data.model.api.notification;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.techsales.taskmanager.data.model.viewmodel.notification.NotificationViewModel;

public class NotificationResponse {
    private String id;

    @SerializedName("user_id")
    private String userId;

    private String name;
    private String description;

    @SerializedName("created_at")
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public static NotificationViewModel mapToViewModel(Context context, NotificationResponse response) {
        return new NotificationViewModel(context, response);
    }
}
