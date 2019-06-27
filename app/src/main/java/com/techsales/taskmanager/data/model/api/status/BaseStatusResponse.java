package com.techsales.taskmanager.data.model.api.status;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.techsales.taskmanager.data.model.viewmodel.status.StatusViewModel;

import java.util.ArrayList;
import java.util.List;

public class BaseStatusResponse {
    @SerializedName("total")
    private int itemCount;

    @SerializedName("data")
    private List<StatusResponse> items;

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public List<StatusResponse> getItems() {
        return items;
    }

    public void setItems(List<StatusResponse> items) {
        this.items = items;
    }

    public static List<StatusViewModel> mapToViewModel(Context context, List<StatusResponse> items) {
        final int count = items.size();
        ArrayList<StatusViewModel> viewModels = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            viewModels.add(new StatusViewModel(context, items.get(i)));
        }
        return viewModels;
    }
}
