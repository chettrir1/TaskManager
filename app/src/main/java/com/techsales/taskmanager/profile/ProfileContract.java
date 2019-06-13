package com.techsales.taskmanager.profile;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;

public interface ProfileContract {

    interface View extends BaseView<Presenter> {

        void showProgress();

        void showDataLoadSuccess();

        void hideProgress();

    }

    interface Presenter extends BasePresenter {

    }
}