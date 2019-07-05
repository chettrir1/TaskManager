package com.techsales.taskmanager.data.model.viewmodel.chooseemployee;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.api.chooseemployee.ChooseEmployeeResponse;
import com.techsales.taskmanager.utils.Constants;

public class ChooseEmployeeViewModel extends BaseObservable {
    private Context context;
    private ChooseEmployeeResponse items;

    public ChooseEmployeeViewModel(Context context, ChooseEmployeeResponse items) {
        this.context = context;
        this.items = items;
    }

    @Bindable
    public String getEmployeeId() {
        return String.valueOf(items.getId());
    }

    @Bindable
    public String getEmployeeName() {
        return items.getFullName();
    }

    @Bindable
    public Drawable getPositionIcon() {
        int userType = items.getUserType();

        if (userType == Constants.COUNT_ZERO) {
            return context.getResources().getDrawable(R.drawable.ic_assign_staff);
        }
        if (userType == Constants.COUNT_ONE) {
            return context.getResources().getDrawable(R.drawable.ic_assign_admin);
        }
        if (userType == Constants.COUNT_TWO) {
            return context.getResources().getDrawable(R.drawable.ic_assign_accountant);
        }
        return context.getResources().getDrawable(R.drawable.ic_assign_admin);
    }

    @Bindable
    public int getPositionIconColor() {
        int userType = items.getUserType();

        if (userType == Constants.COUNT_ZERO) {
            return context.getResources().getColor(R.color.colorGreen);
        }

        if (userType == Constants.COUNT_ONE) {
            return context.getResources().getColor(R.color.colorBlue);
        }

        if (userType == Constants.COUNT_TWO) {
            return context.getResources().getColor(R.color.colorRed);
        }
        return context.getResources().getColor(R.color.colorBlue);
    }

    @Bindable
    public boolean getChecked() {
        return items.isChecked();
    }

    @Bindable
    public void setChecked(boolean checked) {
        items.setChecked(checked);
    }

}
