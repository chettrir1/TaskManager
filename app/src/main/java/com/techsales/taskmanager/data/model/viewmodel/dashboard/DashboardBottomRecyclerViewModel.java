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

    private static final int STATUS_ONE = 0;
    private static final int STATUS_TWO = 1;
    private static final int STATUS_THREE = 2;
    private static final int STATUS_FOUR = 3;

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
        if (status == STATUS_ONE) {
            return STATUS_TYPE_ONE;
        }

        if (status == STATUS_TWO) {
            return STATUS_TYPE_TWO;
        }

        if (status == STATUS_THREE) {
            return STATUS_TYPE_THREE;
        }

        if (status == STATUS_FOUR) {
            return STATUS_TYPE_FOUR;
        }
        return "N/A";
    }

    @Bindable
    public int getTextColor() {
        int status = whereTask.getStatus();
        if (status == STATUS_ONE) {
            return context.getResources().getColor(R.color.colorBlue);
        }

        if (status == STATUS_TWO) {
            return context.getResources().getColor(R.color.colorYellow);
        }

        if (status == STATUS_THREE) {
            return context.getResources().getColor(R.color.colorRed);
        }

        if (status == STATUS_FOUR) {
            return context.getResources().getColor(R.color.colorGreen);
        }

        return context.getResources().getColor(R.color.colorLightBlack);
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

}
