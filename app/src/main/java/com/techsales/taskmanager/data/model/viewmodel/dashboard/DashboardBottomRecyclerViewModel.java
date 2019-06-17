package com.techsales.taskmanager.data.model.viewmodel.dashboard;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.dashboard.bottom.WhereTask;
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

        return Commons.getParsedDay(whereTask.getCreated_at());
    }

    @Bindable
    public String getName() {
        return whereTask.getName();
    }

    @Bindable
    public String getCreatedBy() {
        return String.valueOf(whereTask.getAuthor().getFull_name());
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
    public int getTextColor() {
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

    @Bindable
    public String getTaskDescription() {
        return whereTask.getDescription();
    }

    @Bindable
    public String getTaskAssignedDate() {
        return whereTask.getCreated_at();
    }

    @Bindable
    public String getDeadline() {
        return whereTask.getDeadline();
    }

    @Bindable
    public String getTaskPriority() {
        int priority = whereTask.getPriority();
        if (priority == COUNT_ONE) {
            return PRIORITY_TYPE_ONE;
        }

        if (priority == COUNT_TWO) {
            return PRIORITY_TYPE_TWO;
        }

        if (priority == COUNT_THREE) {
            return PRIORITY_TYPE_THREE;
        }

        return "N/A";
    }

    @Bindable
    public int getPriorityColor() {
        int count = whereTask.getPriority();
        if (count == COUNT_ONE) {
            return context.getResources().getColor(R.color.colorBlue);
        }

        if (count == COUNT_TWO) {
            return context.getResources().getColor(R.color.colorGreen);
        }

        if (count == COUNT_THREE) {
            return context.getResources().getColor(R.color.colorRed);
        }

        return context.getResources().getColor(R.color.colorBlue);
    }

    @Bindable
    public String getClientName() {
        if (whereTask.getClient_name() != null) {
            return whereTask.getClient_name();
        }
        return context.getResources().getString(R.string.text_not_available);
    }

    @Bindable
    public String getClientPhone() {
        if (whereTask.getClient_number() != null) {
            return whereTask.getClient_number();
        }
        return context.getResources().getString(R.string.text_not_available);
    }
}
