package com.techsales.taskmanager.dashboard.container;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;

public interface DashboardContract {
    interface View extends BaseView<Presenter> {
        void setToolbarTitle(String type);

        void showProgress();

        void onLogoutSelected();

        void showNetworkNotAvailableError(String message);

        void showError(String message);
    }

    interface Presenter extends BasePresenter {

        void onLogout();

    }
}
