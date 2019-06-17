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
        return userInfo.getUserName();
    }

    @Bindable
    public String getUserId() {
        return userInfo.getId();
    }

    public UserInfo getUserInfo(){
        /* if your user model is changed then update
         userModel here and return it. And you can use
         this model to pass from intent or from bundle
         but this userInfo model must implement serializable */
        return this.userInfo;
    }
}
