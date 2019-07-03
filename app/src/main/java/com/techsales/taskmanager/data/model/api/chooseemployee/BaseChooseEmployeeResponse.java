package com.techsales.taskmanager.data.model.api.chooseemployee;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.techsales.taskmanager.data.model.viewmodel.chooseemployee.ChooseEmployeeViewModel;

import java.util.ArrayList;
import java.util.List;

public class BaseChooseEmployeeResponse {

    @SerializedName("data")
    private List<ChooseEmployeeResponse> items;

    public List<ChooseEmployeeResponse> getChooseEmployeeResponse() {
        return items;
    }

    public void setItems(List<ChooseEmployeeResponse> items) {
        this.items = items;
    }

    public static List<ChooseEmployeeViewModel> mapToViewModel(Context context, List<ChooseEmployeeResponse> items) {
        final int count = items.size();
        ArrayList<ChooseEmployeeViewModel> viewModels = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            viewModels.add(new ChooseEmployeeViewModel(context, items.get(i)));
        }
        return viewModels;
    }
}
