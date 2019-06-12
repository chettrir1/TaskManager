package com.techsales.taskmanager.data.model.dashboard.top;

public class TopRecyclerItems {
    private String taskCount, taskName;
    private int taskIcon, taskColor;

    public TopRecyclerItems(String taskCount, String taskName, int taskIcon, int taskColor) {
        this.taskCount = taskCount;
        this.taskName = taskName;
        this.taskIcon = taskIcon;
        this.taskColor = taskColor;
    }

    public String getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(String taskCount) {
        this.taskCount = taskCount;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskIcon() {
        return taskIcon;
    }

    public void setTaskIcon(int taskIcon) {
        this.taskIcon = taskIcon;
    }

    public int getTaskColor() {
        return taskColor;
    }

    public void setTaskColor(int taskColor) {
        this.taskColor = taskColor;
    }
}
