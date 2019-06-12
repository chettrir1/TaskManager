package com.techsales.taskmanager.data.model.viewmodel.dashboard;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.data.model.dashboard.top.TopRecyclerItems;


public class DashboardTopRecyclerViewModel extends BaseObservable {

    private TopRecyclerItems topRecyclerItems;
    private Context context;

    public DashboardTopRecyclerViewModel(Context context, TopRecyclerItems topRecyclerItems) {
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
