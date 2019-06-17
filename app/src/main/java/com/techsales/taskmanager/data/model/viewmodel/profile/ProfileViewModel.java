package com.techsales.taskmanager.data.model.viewmodel.profile;

import com.techsales.taskmanager.data.model.UserInfo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class ProfileViewModel extends BaseObservable {
    private UserInfo userInfo;

    public ProfileViewModel(UserInfo userInfo){
        this.userInfo = userInfo;
    }

    @Bindable
    public String getImagePath(){
        return userInfo.getProfileImage();
    }
}
