package com.techsales.taskmanager.data.model.viewmodel.dashboard;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.data.model.dashboard.bottom.AllTasks;

public class DashboardBottomRecyclerViewModel extends BaseObservable {

    private AllTasks taskInfo;
/*
    private WhereTask whereTask;
*/
    public DashboardBottomRecyclerViewModel(AllTasks taskInfo) {
        this.taskInfo = taskInfo;
/*
        this.whereTask = getWhereTask();
*/
    }

    @Bindable
    public String getDay() {
        return taskInfo.getCreated_at();
    }

    @Bindable
    public String getName() {
        return taskInfo.getFull_name();
    }

    @Bindable
    public String getCreatedBy() {
        return taskInfo.getFull_name();
    }

    @Bindable
    public String getStatus() {
        return taskInfo.getFull_name();
    }


   /* private WhereTask getWhereTask() {
        for (int i = 0; i < taskInfo.getWhere_task().size(); i++) {
            whereTask = new WhereTask();
            whereTask.setAuthor(taskInfo.getWhere_task().get(i).getAuthor());
            whereTask.setClient_latitude(taskInfo.getWhere_task().get(i).getClient_latitude());
            whereTask.setClient_longitude(taskInfo.getWhere_task().get(i).getClient_latitude());
            whereTask.setClient_name(taskInfo.getWhere_task().get(i).getClient_name());
            whereTask.setClient_number(taskInfo.getWhere_task().get(i).getClient_number());
            whereTask.setCreated_at(taskInfo.getWhere_task().get(i).getCreated_at());
            whereTask.setCreated_by(taskInfo.getWhere_task().get(i).getCreated_by());
            whereTask.setDeadline(taskInfo.getWhere_task().get(i).getDeadline());
            whereTask.setDescription(taskInfo.getWhere_task().get(i).getDescription());
            whereTask.setName(taskInfo.getWhere_task().get(i).getName());
            whereTask.setStatus(taskInfo.getWhere_task().get(i).getStatus());
        }

        return whereTask;
    }*/
}
