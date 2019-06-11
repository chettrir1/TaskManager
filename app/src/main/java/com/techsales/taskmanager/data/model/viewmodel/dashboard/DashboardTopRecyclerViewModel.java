package com.techsales.taskmanager.data.model.viewmodel.dashboard;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.data.model.TopRecyclerItems;


public class DashboardTopRecyclerViewModel extends BaseObservable {

    private TopRecyclerItems topRecyclerItems;

    public DashboardTopRecyclerViewModel(TopRecyclerItems topRecyclerItems) {
        this.topRecyclerItems = topRecyclerItems;
    }

    @Bindable
    public String getTaskCount() {
        return topRecyclerItems.getTaskCount();
    }

    @Bindable
    public int getTaskIcon() {
        return topRecyclerItems.getTaskIcon();
    }

    @Bindable
    public String getTaskName() {
        return topRecyclerItems.getTaskName();
    }

    @Bindable
    public int getTaskColor() {
        return topRecyclerItems.getTaskColor();
    }
}
