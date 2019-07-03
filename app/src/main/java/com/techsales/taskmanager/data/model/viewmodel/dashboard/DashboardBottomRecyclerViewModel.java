package com.techsales.taskmanager.data.model.viewmodel.dashboard;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.api.dashboard.WhereTask;
import com.techsales.taskmanager.data.model.viewtask.TaskDetails;
import com.techsales.taskmanager.utils.Commons;

import static com.techsales.taskmanager.utils.Constants.COUNT_THREE;
import static com.techsales.taskmanager.utils.Constants.COUNT_ZERO;
import static com.techsales.taskmanager.utils.Constants.COUNT_TWO;
import static com.techsales.taskmanager.utils.Constants.COUNT_ONE;
import static com.techsales.taskmanager.utils.Constants.STATUS_TYPE_FOUR;
import static com.techsales.taskmanager.utils.Constants.STATUS_TYPE_ONE;
import static com.techsales.taskmanager.utils.Constants.STATUS_TYPE_THREE;
import static com.techsales.taskmanager.utils.Constants.STATUS_TYPE_TWO;

public class DashboardBottomRecyclerViewModel extends BaseObservable {
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
        if (status == COUNT_ZERO) {
            return STATUS_TYPE_ONE;
        }

        if (status == COUNT_ONE) {
            return STATUS_TYPE_TWO;
        }

        if (status == COUNT_TWO) {
            return STATUS_TYPE_THREE;
        }

        if (status == COUNT_THREE) {
            return STATUS_TYPE_FOUR;
        }
        return "N/A";
    }

    @Bindable
    public int getBackgroundColor() {
        int count = whereTask.getStatus();
        if (count == COUNT_ZERO) {
            return context.getResources().getColor(R.color.colorBlue);
        }

        if (count == COUNT_ONE) {
            return context.getResources().getColor(R.color.colorYellow);
        }

        if (count == COUNT_TWO) {
            return context.getResources().getColor(R.color.colorRed);
        }

        if (count == COUNT_THREE) {
            return context.getResources().getColor(R.color.colorGreen);
        }
        return context.getResources().getColor(R.color.colorBlue);
    }

    public TaskDetails getTaskDetails() {
        TaskDetails taskDetails = new TaskDetails();
        taskDetails.setTaskId(whereTask.getId());
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
