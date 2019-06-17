package com.techsales.taskmanager.profile;

import com.techsales.taskmanager.data.model.UserInfo;
import com.techsales.taskmanager.data.model.viewmodel.profile.ProfileViewModel;
import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;

import io.reactivex.disposables.Disposable;

public class ProfilePresenter implements ProfileContract.Presenter {
    private final TaskManagerComponent component;
    private ProfileContract.View view;
    private Disposable disposable;

    ProfilePresenter(TaskManagerComponent component, ProfileContract.View view) {
        this.component = component;
        this.view = view;
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
        Commons.dispose(disposable);
    }

    @Override
    public ProfileViewModel getProfileViewModel() {
            UserInfo userInfo = component.data().savedUserInfo();
            if (userInfo!= null){
               return UserInfo.mapToProfileViewModel(userInfo);
            }

        return null;
    }
}
