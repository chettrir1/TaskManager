package com.techsales.taskmanager.data.model.viewmodel.taskdetails;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewtask.TaskDetails;
import com.techsales.taskmanager.utils.Commons;

import static com.techsales.taskmanager.utils.Constants.COUNT_ONE;
import static com.techsales.taskmanager.utils.Constants.COUNT_THREE;
import static com.techsales.taskmanager.utils.Constants.COUNT_TWO;
import static com.techsales.taskmanager.utils.Constants.COUNT_ZERO;
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
        if (TextUtils.isEmpty(taskDetails.getTaskDescription())) {
            return context.getResources()
                    .getString(R.string.view_task_fragment_task_desc_not_available);
        }
        return taskDetails.getTaskDescription();
    }

    public String getAssignedDate() {
        return Commons.dateParse(taskDetails.getAssignedDate());
    }

    public String getDeadline() {
        return taskDetails.getDeadline();
    }

    public String getTaskStatus() {
        int status = taskDetails.getTaskStatus();
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
        return context.getResources().getString(R.string.task_details_error_no_data);
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
        return context.getResources().getString(R.string.task_details_error_no_data);
    }

    public String getClientName() {
        return Commons.capitalize(taskDetails.getClientName());
    }

    public String getClientPhone() {
        if (TextUtils.isEmpty(taskDetails.getClientPhone())) {
            return context.getResources()
                    .getString(R.string.view_task_fragment_client_phone_not_available);
        }
        return taskDetails.getClientPhone();
    }


    @Bindable
    public Drawable getTaskStatusDrawable() {
        int status = taskDetails.getTaskStatus();
        if (status == COUNT_ZERO) {
            return context.getResources().getDrawable(R.drawable.ic_new_task);
        }

        if (status == COUNT_ONE) {
            return context.getResources().getDrawable(R.drawable.ic_opened_task);
        }

        if (status == COUNT_TWO) {
            return context.getResources().getDrawable(R.drawable.ic_pending_task);
        }

        if (status == COUNT_THREE) {
            return context.getResources().getDrawable(R.drawable.ic_completed_task);
        }
        return context.getResources().getDrawable(R.drawable.ic_new_task);
    }

    @Bindable
    public Drawable getPriorityDrawable() {
        int status = taskDetails.getTaskPriority();

        if (status == COUNT_ONE) {
            return context.getResources().getDrawable(R.drawable.ic_priority_urgent);
        }

        if (status == COUNT_TWO) {
            return context.getResources().getDrawable(R.drawable.ic_priority_important);
        }

        if (status == COUNT_THREE) {
            return context.getResources().getDrawable(R.drawable.ic_priority_urgent);
        }
        return context.getResources().getDrawable(R.drawable.ic_new_task);
    }

}
