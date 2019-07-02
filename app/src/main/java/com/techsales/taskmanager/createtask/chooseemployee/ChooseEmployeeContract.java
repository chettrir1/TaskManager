package com.techsales.taskmanager.createtask.chooseemployee;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;

public interface ChooseEmployeeContract {
    interface View extends BaseView<Presenter> {
        void showProgress();

        void showLoadingError(String message);

        void showNoNetworkAvailable();

        void showLoadingSuccess();
    }

    interface Presenter extends BasePresenter {

    }
}
