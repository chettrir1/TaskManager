package com.techsales.taskmanager.data.model;

import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardTopRecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

public class TopItems {
    private List<TopRecyclerItems> items;

    public List<TopRecyclerItems> getItems() {
        return items;
    }

    public void setItems(List<TopRecyclerItems> items) {
        this.items = items;
    }

    public static List<DashboardTopRecyclerViewModel> mapToViewModel(List<TopRecyclerItems> items) {
        final int count = items.size();
        ArrayList<DashboardTopRecyclerViewModel> viewModels = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            viewModels.add(new DashboardTopRecyclerViewModel(items.get(i)));
        }
        return viewModels;
    }
}
