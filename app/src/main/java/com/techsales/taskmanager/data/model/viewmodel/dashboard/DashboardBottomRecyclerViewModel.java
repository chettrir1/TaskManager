package com.techsales.taskmanager.data.model.viewmodel.dashboard;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.api.dashboard.WhereTask;
import com.techsales.taskmanager.data.model.viewtask.TaskDetails;
import com.techsales.taskmanager.utils.Commons;

public class DashboardBottomRecyclerViewModel extends BaseObservable {
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

    private WhereTask whereTask;

    private Context context;


    public DashboardBottomRecyclerViewModel(Context context, WhereTask whereTask) {
        this.context = context;
        this.whereTask = whereTask;
    }

    @Bindable
    public String getDay() {

        return Commons.getParsedDay(whereTask.getCreatedAt());
    }

    @Bindable
    public String getName() {
        return whereTask.getName();
    }

    @Bindable
    public String getCreatedBy() {
        return String.valueOf(whereTask.getAuthor().getFullName());
    }

    @Bindable
    public String getStatus() {
        int status = whereTask.getStatus();
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
        return "N/A";
    }

    @Bindable
    public int getBackgroundColor() {
        int count = whereTask.getStatus();
        if (count == COUNT_ONE) {
            return context.getResources().getColor(R.color.colorBlue);
        }

        if (count == COUNT_TWO) {
            return context.getResources().getColor(R.color.colorYellow);
        }

        if (count == COUNT_THREE) {
            return context.getResources().getColor(R.color.colorRed);
        }

        if (count == COUNT_FOUR) {
            return context.getResources().getColor(R.color.colorGreen);
        }
        return context.getResources().getColor(R.color.colorBlue);
    }

    public TaskDetails getTaskDetails() {
        TaskDetails taskDetails = new TaskDetails();
        taskDetails.setTaskName(whereTask.getName());
        taskDetails.setTaskDescription(whereTask.getDescription());
        taskDetails.setAssignedDate(whereTask.getCreatedAt());
        taskDetails.setDeadline(whereTask.getDeadline());
        taskDetails.setTaskStatus(whereTask.getStatus());
        taskDetails.setTaskPriority(whereTask.getPriority());
        taskDetails.setClientName(whereTask.getClientName());
        taskDetails.setClientPhone(whereTask.getClientNumber());
        return taskDetails;
    }

}
