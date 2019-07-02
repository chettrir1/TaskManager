package com.techsales.taskmanager.dashboard.container;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;

public interface DashboardContract {
    interface View extends BaseView<Presenter> {
        void setToolbarTitle(String type);
    }

    interface Presenter extends BasePresenter {

    }
}
