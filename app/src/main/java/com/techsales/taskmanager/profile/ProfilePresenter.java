package com.techsales.taskmanager.profile;

import com.techsales.taskmanager.data.model.login.UserInfo;
import com.techsales.taskmanager.data.model.viewmodel.profile.ProfileViewModel;
import com.techsales.taskmanager.di.TaskManagerComponent;

public class ProfilePresenter implements ProfileContract.Presenter {
    private final TaskManagerComponent component;
    private ProfileContract.View view;

    ProfilePresenter(TaskManagerComponent component, ProfileContract.View view) {
        this.component = component;
        this.view = view;
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public ProfileViewModel getProfileViewModel() {
        UserInfo userInfo = component.data().savedUserInfo();
        if (userInfo != null) {
            return UserInfo.mapToProfileViewModel(userInfo);
        }

        return null;
    }
}
