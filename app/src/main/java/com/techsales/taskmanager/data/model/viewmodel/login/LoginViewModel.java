package com.techsales.taskmanager.data.model.viewmodel.login;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.data.model.UserInfo;

public class LoginViewModel extends BaseObservable {
    private UserInfo userInfo;

    public LoginViewModel(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Bindable
    public String getUsername() {
        return userInfo.getUser_name();
    }

    @Bindable
    public String getUserId() {
        return userInfo.getId();
    }
}
