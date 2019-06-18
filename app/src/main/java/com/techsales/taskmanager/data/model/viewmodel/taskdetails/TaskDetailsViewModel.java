package com.techsales.taskmanager.data.model.viewmodel.taskdetails;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewtask.TaskDetails;

public class TaskDetailsViewModel extends BaseObservable {

    private static final String STATUS_TYPE_ONE = "NEW";
    private static final String STATUS_TYPE_TWO = "OPENED";
    private static final String STATUS_TYPE_THREE = "PENDING";
    private static final String STATUS_TYPE_FOUR = "COMPLETED";

    private static final int COUNT_ONE = 0;
    private static final int COUNT_TWO = 1;
    private static final int COUNT_THREE = 2;
    private static final int COUNT_FOUR = 3;


    private static final String PRIORITY_TYPE_ONE = "NORMAL";
    private static final String PRIORITY_TYPE_TWO = "IMPORTANT";
    private static final String PRIORITY_TYPE_THREE = "URGENT";


    private TaskDetails taskDetails;
    private Context context;

    public TaskDetailsViewModel(Context context, TaskDetails taskDetails) {
        this.context = context;
        this.taskDetails = taskDetails;

    }

    public String getTaskName() {
        return taskDetails.getTaskName();
    }

    public String getTaskDescription() {
        return taskDetails.getTaskDescription();
    }

    public String getAssignedDate() {
        return taskDetails.getAssignedDate();
    }

    public String getDeadline() {
        return taskDetails.getDeadline();
    }

    public String getTaskStatus() {
        int status = taskDetails.getTaskStatus();
        if (status == COUNT_ONE) {
            return STATUS_TYPE_ONE;
        }

        if (status == COUNT_TWO) {
            return STATUS_TYPE_TWO;
        }

        if (status == COUNT_THREE) {
            return STATUS_TYPE_THREE;
        }

        if (status == COUNT_FOUR) {
            return STATUS_TYPE_FOUR;
        }
        return context.getResources().getString(R.string.no_tasks_available);
    }

    public String getTaskPriority() {
        int priority = taskDetails.getTaskPriority();
        if (priority == COUNT_ONE) {
            return PRIORITY_TYPE_ONE;
        }

        if (priority == COUNT_TWO) {
            return PRIORITY_TYPE_TWO;
        }

        if (priority == COUNT_THREE) {
            return PRIORITY_TYPE_THREE;
        }
        return context.getResources().getString(R.string.no_tasks_available);
    }

    public String getClientName() {
        return taskDetails.getClientName();
    }

    public String getClientPhone() {
        return taskDetails.getClientPhone();
    }


    @Bindable
    public int getTaskStatusColor() {
        int status = taskDetails.getTaskStatus();
        if (status == COUNT_ONE) {
            return context.getResources().getColor(R.color.colorBlue);
        }

        if (status == COUNT_TWO) {
            return context.getResources().getColor(R.color.colorYellow);
        }

        if (status == COUNT_THREE) {
            return context.getResources().getColor(R.color.colorRed);
        }

        if (status == COUNT_FOUR) {
            return context.getResources().getColor(R.color.colorGreen);
        }
        return context.getResources().getColor(R.color.colorBlue);
    }

    @Bindable
    public int getTaskPriorityColor() {
        int status = taskDetails.getTaskPriority();
        if (status == COUNT_ONE) {
            return context.getResources().getColor(R.color.colorBlue);
        }

        if (status == COUNT_TWO) {
            return context.getResources().getColor(R.color.colorYellow);
        }

        if (status == COUNT_THREE) {
            return context.getResources().getColor(R.color.colorRed);
        }

        if (status == COUNT_FOUR) {
            return context.getResources().getColor(R.color.colorGreen);
        }
        return context.getResources().getColor(R.color.colorBlue);
    }


}
