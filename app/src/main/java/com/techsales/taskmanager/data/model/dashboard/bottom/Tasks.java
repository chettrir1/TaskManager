package com.techsales.taskmanager.data.model.dashboard.bottom;

import android.content.Context;

import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardBottomRecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

public class Tasks {

    private int itemCount;
    private List<AllTasks> items;

    public List<AllTasks> getItems() {
        return items;
    }

    public void setItems(List<AllTasks> items) {
        this.items = items;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }


    public static List<DashboardBottomRecyclerViewModel> mapToViewModel(List<AllTasks> items) {
        final int count = items.size();
        ArrayList<DashboardBottomRecyclerViewModel> viewModels = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            viewModels.add(new DashboardBottomRecyclerViewModel(items.get(i)));
        }
        return viewModels;
    }
}
