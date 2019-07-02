package com.techsales.taskmanager.data.model.viewmodel.chooseemployee;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.databinding.BaseObservable;

import com.techsales.taskmanager.data.model.api.chooseemployee.ChooseEmployeeResponse;

public class ChooseEmployeeViewModel extends BaseObservable {
    private Context context;
    private ChooseEmployeeResponse items;

    public ChooseEmployeeViewModel(Context context, ChooseEmployeeResponse items) {
        this.context = context;
        this.items = items;
    }

    public String getEmployeeName() {
        return items.getFullName();
    }

    public Drawable getPositionIcon() {
        int usertype = items.getUserType();

        return context.getResources().getDrawable(items.getUserType());
    }


}
