package com.techsales.taskmanager.profile;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;
import com.techsales.taskmanager.data.model.UserInfo;
import com.techsales.taskmanager.data.model.viewmodel.profile.ProfileViewModel;

public interface ProfileContract {

    interface View extends BaseView<Presenter> {

        void showProgress();

        void showDataLoadSuccess();

        void hideProgress();

    }

    interface Presenter extends BasePresenter {
        ProfileViewModel getProfileViewModel();
    }
}
