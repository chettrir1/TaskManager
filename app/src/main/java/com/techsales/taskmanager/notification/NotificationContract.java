package com.techsales.taskmanager.notification;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;
import com.techsales.taskmanager.data.model.viewmodel.notification.NotificationViewModel;

public interface NotificationContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void showLoadingError(String message);

        void showLoadingSuccess(NotificationViewModel viewModel);

        void showNetworkNotAvailable();

    }

    interface Presenter extends BasePresenter {

        void getAllNotification(String userId);
    }
}
