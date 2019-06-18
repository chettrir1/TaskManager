package com.techsales.taskmanager.data.model.viewtask;

import android.content.Context;

import com.techsales.taskmanager.data.model.viewmodel.taskdetails.TaskDetailsViewModel;

import java.io.Serializable;

public class TaskDetails implements Serializable {
    private String taskName;
    private String taskDescription;
    private String assignedDate;
    private String deadline;
    private int taskStatus;
    private int taskPriority;
    private String clientName;
    private String clientPhone;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(int taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public static TaskDetailsViewModel mapToTaskDetailsViewModel(Context context, TaskDetails taskDetails) {
        return new TaskDetailsViewModel(context, taskDetails);
    }
}
