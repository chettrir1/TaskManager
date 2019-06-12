package com.techsales.taskmanager.data.model.viewmodel.dashboard;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.data.model.dashboard.top.TaskStatus;


public class DashboardTopRecyclerViewModel extends BaseObservable {

    private TaskStatus topRecyclerItems;
    private Context context;

    public DashboardTopRecyclerViewModel(Context context, TaskStatus topRecyclerItems) {
        this.topRecyclerItems = topRecyclerItems;
        this.context = context;
    }

    @Bindable
    public String getTaskCount() {
        return topRecyclerItems.getTaskCount();
    }

    @Bindable
    public Drawable getTaskIcon() {
        return context.getResources().getDrawable(topRecyclerItems.getTaskIcon());
    }

    @Bindable
    public String getTaskName() {
        return topRecyclerItems.getTaskName();
    }

    @Bindable
    public Drawable getTaskColor() {
        return context.getResources().getDrawable(topRecyclerItems.getTaskColor());
    }
}
