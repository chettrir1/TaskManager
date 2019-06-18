package com.techsales.taskmanager.data.model.viewmodel.profile;

import com.techsales.taskmanager.data.model.login.UserInfo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class ProfileViewModel extends BaseObservable {
    private UserInfo userInfo;

    public ProfileViewModel(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Bindable
    public String getImagePath() {
        return userInfo.getProfileImage();
    }

    @Bindable
    public String getUserName() {
        return userInfo.getUserName();
    }

    @Bindable
    public String getFullName() {
        return userInfo.getFullName();
    }

    @Bindable
    public String getEmail() {
        return userInfo.getEmail();
    }

    @Bindable
    public String getContact() {
        return userInfo.getContact();
    }

    @Bindable
    public String getPrimaryAddress() {
        return userInfo.getPermanentAddress();
    }

    @Bindable
    public String getTemporaryAddress() {
        return userInfo.getTemporaryAddress();
    }
}
