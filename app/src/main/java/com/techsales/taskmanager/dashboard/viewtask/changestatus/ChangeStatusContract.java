package com.techsales.taskmanager.dashboard.viewtask.changestatus;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;

public interface ChangeStatusContract {
    interface View extends BaseView<Presenter> {
        void showProgress();

        void showChangeStatusError(String message);

        void showNetworkNotAvailableError();

        void showChangeStatusSuccess(String message);
    }

    interface Presenter extends BasePresenter {
        void changeStatus(String task_id, String status, String remarks);
    }
}
