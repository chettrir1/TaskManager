package com.techsales.taskmanager.data.model.dashboard.bottom;

import com.google.gson.annotations.SerializedName;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardBottomRecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

public class BaseTasksResponse {

    private int itemCount;
    @SerializedName("where_task")
    private List<WhereTask> whereTasks;

    public List<WhereTask> getwhereTasks() {
        return whereTasks;
    }

    public void setwhereTasks(List<WhereTask> whereTasks) {
        this.whereTasks = whereTasks;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }


    public static List<DashboardBottomRecyclerViewModel> mapToViewModel(List<WhereTask> items) {
        final int count = items.size();
        ArrayList<DashboardBottomRecyclerViewModel> viewModels = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            viewModels.add(new DashboardBottomRecyclerViewModel(items.get(i)));
        }
        return viewModels;
    }
}
