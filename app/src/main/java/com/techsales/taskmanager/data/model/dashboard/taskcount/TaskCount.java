package com.techsales.taskmanager.data.model.dashboard.taskcount;

import com.google.gson.annotations.SerializedName;

public class TaskCount {
    @SerializedName("new")
    private String taskNew;

    @SerializedName("opened")
    private String taskOpened;

    @SerializedName("pending")
    private String taskPending;

    @SerializedName("completed")
    private String taskCompleted;

    public String getTaskNew() {
        return taskNew;
    }

    public void setTaskNew(String taskNew) {
        this.taskNew = taskNew;
    }

    public String getTaskOpened() {
        return taskOpened;
    }

    public void setTaskOpened(String taskOpened) {
        this.taskOpened = taskOpened;
    }

    public String getTaskPending() {
        return taskPending;
    }

    public void setTaskPending(String taskPending) {
        this.taskPending = taskPending;
    }

    public String getTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(String taskCompleted) {
        this.taskCompleted = taskCompleted;
    }
}
