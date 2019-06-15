package com.techsales.taskmanager.data.model.viewmodel.dashboard;

import com.techsales.taskmanager.data.model.dashboard.bottom.WhereTask;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class DashboardBottomRecyclerViewModel extends BaseObservable {
    private static final String STATUS_TYPE_ONE = "NEW";
    private static final String STATUS_TYPE_TWO = "OLD";
    private static final int STATUS_ONE = 1;
    private static final int STATUS_TWO = 2;
    private WhereTask whereTask;

    public DashboardBottomRecyclerViewModel(WhereTask whereTask) {
        this.whereTask = whereTask;
    }

    @Bindable
    public String getDay() {

        return whereTask.getCreated_at();
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
    public String getStatus()
    {
        int status = whereTask.getStatus();
        if (status == STATUS_ONE){
            return STATUS_TYPE_ONE;
        }

        if (status == STATUS_TWO) {
            return STATUS_TYPE_TWO;
        }
        return "N/A";
    }

}
