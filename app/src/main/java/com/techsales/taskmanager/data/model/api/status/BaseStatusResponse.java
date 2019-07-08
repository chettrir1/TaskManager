package com.techsales.taskmanager.data.model.api.status;

import android.content.Context;

import com.techsales.taskmanager.data.model.viewmodel.status.StatusViewModel;

import java.util.ArrayList;
import java.util.List;

public class BaseStatusResponse {
    private int total;
    private List<StatusResponse> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<StatusResponse> getItems() {
        return data;
    }

    public void setItems(List<StatusResponse> items) {
        this.data = items;
    }

    public static List<StatusViewModel> mapToViewModel(Context context, List<StatusResponse> items) {
        final int count = items.size();
        ArrayList<StatusViewModel> viewModels = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            viewModels.add(new StatusViewModel(context, items.get(i)));
        }
        return viewModels;
    }
}
