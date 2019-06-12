package com.techsales.taskmanager.data.model.viewmodel.dashboard;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.data.model.dashboard.bottom.AllTasks;

public class DashboardBottomRecyclerViewModel extends BaseObservable {

    private AllTasks taskInfo;

    public DashboardBottomRecyclerViewModel(AllTasks taskInfo) {
        this.taskInfo = taskInfo;
    }

    @Bindable
    public String getDay() {
        return taskInfo.getWhere_task().get(0).getCreated_at();
    }

    @Bindable
    public String getName() {
        return taskInfo.getWhere_task().get(0).getName();
    }

    @Bindable
    public String getCreatedBy() {
        return String.valueOf(taskInfo.getWhere_task().get(0).getCreated_by());
    }

    @Bindable
    public String getStatus() {
        return String.valueOf(taskInfo.getWhere_task().get(0).getStatus());
    }

}
