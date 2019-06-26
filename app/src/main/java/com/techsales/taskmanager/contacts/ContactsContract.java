package com.techsales.taskmanager.contacts;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;

public interface ContactsContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void showLoadingError(String message);

        void showLoadingSuccess();

        void showNoNetworkAvailable();
    }

    interface Presenter extends BasePresenter {

    }
}
