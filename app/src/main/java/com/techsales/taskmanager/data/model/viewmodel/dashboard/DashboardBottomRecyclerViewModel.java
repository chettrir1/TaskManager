package com.techsales.taskmanager.data.model.viewmodel.dashboard;

import androidx.databinding.BaseObservable;

import com.techsales.taskmanager.data.model.dashboard.bottom.BottomItems;

public class DashboardBottomRecyclerViewModel extends BaseObservable {

    private BottomItems bottomItems;

    public DashboardBottomRecyclerViewModel(BottomItems bottomItems) {
        this.bottomItems = bottomItems;
    }

}
