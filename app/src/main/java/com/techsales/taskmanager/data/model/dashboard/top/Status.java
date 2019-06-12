package com.techsales.taskmanager.data.model.dashboard.top;

import android.content.Context;

import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardTopRecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

public class Status {
    private List<TaskStatus> items;

    public List<TaskStatus> getItems() {
        return items;
    }

    public void setItems(List<TaskStatus> items) {
        this.items = items;
    }

    public static List<DashboardTopRecyclerViewModel> mapToViewModel(Context context, List<TaskStatus> items) {
        final int count = items.size();
        ArrayList<DashboardTopRecyclerViewModel> viewModels = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            viewModels.add(new DashboardTopRecyclerViewModel(context, items.get(i)));
        }
        return viewModels;
    }
}
