package com.techsales.taskmanager.data.model.viewmodel.status;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.api.status.StatusResponse;

import static com.techsales.taskmanager.utils.Constants.COUNT_THREE;
import static com.techsales.taskmanager.utils.Constants.COUNT_ZERO;
import static com.techsales.taskmanager.utils.Constants.COUNT_TWO;
import static com.techsales.taskmanager.utils.Constants.COUNT_ONE;

public class StatusViewModel extends BaseObservable {
    private StatusResponse items;
    private Context context;

    public StatusViewModel(Context context, StatusResponse items) {
        this.context = context;
        this.items = items;
    }

    @Bindable
    public String getTaskTitle() {
        return items.getName();
    }

    @Bindable
    public String getTaskDescription() {
        return items.getDescription();
    }

    @Bindable
    public String getTaskAuthor() {
        return String.format("Assigned by: %s", items.getAuthor());
    }

    @Bindable
    public int getStatusColor() {
        int count = items.getStatus();

        if (count == COUNT_ZERO) {
            return context.getResources().getColor(R.color.colorBlue);
        }

        if (count == COUNT_ONE) {
            return context.getResources().getColor(R.color.colorYellow);
        }

        if (count == COUNT_TWO) {
            return context.getResources().getColor(R.color.colorRed);
        }

        if (count == COUNT_THREE) {
            return context.getResources().getColor(R.color.colorGreen);

        }

        return context.getResources().getColor(R.color.colorBlue);
    }

    @Bindable
    public Drawable getDrawable() {
        int count = items.getStatus();

        if (count == COUNT_ZERO) {
            return context.getResources().getDrawable(R.drawable.ic_new_task);
        }

        if (count == COUNT_ONE) {
            return context.getResources().getDrawable(R.drawable.ic_opened_task);
        }

        if (count == COUNT_TWO) {
            return context.getResources().getDrawable(R.drawable.ic_pending_task);
        }

        if (count == COUNT_THREE) {
            return context.getResources().getDrawable(R.drawable.ic_completed_task);

        }

        return context.getResources().getDrawable(R.drawable.ic_new_task);
    }
}
