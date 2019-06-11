package com.techsales.taskmanager.auth.login;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;
import com.techsales.taskmanager.data.model.viewmodel.login.LoginViewModel;

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void showEmptyUsername(String message);

        void showEmptyPassword(String message);

        void showLoginProgress();

        void showLoginSuccess(LoginViewModel loginViewModel);

        void showLoginError(String message);

        void showNetworkNotAvailableError();
    }

    interface Presenter extends BasePresenter {
        void onLogin(String username, String password, String token);

    }
}
