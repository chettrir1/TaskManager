package com.techsales.taskmanager.data.model.viewmodel.taskdetails;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewtask.TaskDetails;

import static com.techsales.taskmanager.utils.Constants.COUNT_FOUR;
import static com.techsales.taskmanager.utils.Constants.COUNT_ONE;
import static com.techsales.taskmanager.utils.Constants.COUNT_THREE;
import static com.techsales.taskmanager.utils.Constants.COUNT_TWO;
import static com.techsales.taskmanager.utils.Constants.PRIORITY_TYPE_ONE;
import static com.techsales.taskmanager.utils.Constants.PRIORITY_TYPE_THREE;
import static com.techsales.taskmanager.utils.Constants.PRIORITY_TYPE_TWO;
import static com.techsales.taskmanager.utils.Constants.STATUS_TYPE_FOUR;
import static com.techsales.taskmanager.utils.Constants.STATUS_TYPE_ONE;
import static com.techsales.taskmanager.utils.Constants.STATUS_TYPE_THREE;
import static com.techsales.taskmanager.utils.Constants.STATUS_TYPE_TWO;

public class TaskDetailsViewModel extends BaseObservable {


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
        if (priority == COUNT_TWO) {
            return PRIORITY_TYPE_ONE;
        }

        if (priority == COUNT_THREE) {
            return PRIORITY_TYPE_TWO;
        }

        if (priority == COUNT_FOUR) {
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

        if (status == COUNT_TWO) {
            return context.getResources().getColor(R.color.colorGreen);
        }

        if (status == COUNT_THREE) {
            return context.getResources().getColor(R.color.colorYellow);
        }

        if (status == COUNT_FOUR) {
            return context.getResources().getColor(R.color.colorRed);
        }
        return context.getResources().getColor(R.color.colorBlue);
    }

}
