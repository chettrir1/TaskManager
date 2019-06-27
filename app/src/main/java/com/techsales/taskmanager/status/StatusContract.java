package com.techsales.taskmanager.status;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;

public interface StatusContract {
    interface View extends BaseView<Presenter> {
        void showProgress();

        void showLoadingError(String message);

        void showLoadingSuccess();

        void showNoNetworkAvailableError();

    }

    interface Presenter extends BasePresenter {

    }
}
