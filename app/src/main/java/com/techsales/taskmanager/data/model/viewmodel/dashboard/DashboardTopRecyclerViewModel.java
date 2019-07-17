package com.techsales.taskmanager.data.model.viewmodel.dashboard;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.dashboard.top.TaskStatus;
import com.techsales.taskmanager.utils.Constants;


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

    @Bindable
    public String getStatusNum() {
        String name = topRecyclerItems.getTaskName();
        if (name.equals(context.getString(R.string.dashboard_task_text_new)))
            return String.valueOf(Constants.COUNT_ZERO);

        if (name.equals(context.getString(R.string.dashboard_task_text_open)))
            return String.valueOf(Constants.COUNT_ONE);

        if (name.equals(context.getString(R.string.dashboard_task_text_pending)))
            return String.valueOf(Constants.COUNT_TWO);

        if (name.equals(context.getString(R.string.dashboard_task_text_complete)))
            return String.valueOf(Constants.COUNT_THREE);

        return String.valueOf(Constants.COUNT_ONE);
    }
}
