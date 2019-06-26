package com.techsales.taskmanager.notification;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;
import com.techsales.taskmanager.data.model.viewmodel.notification.NotificationViewModel;

import java.util.List;

public interface NotificationContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void showLoadingError(String message);

        void showLoadingSuccess(List<NotificationViewModel> viewModel);

        void showNetworkNotAvailable();

        void onRecyclerItemClicked(NotificationViewModel viewModel, int position);

    }

    interface Presenter extends BasePresenter, NotificationRecyclerAdapter.NotificationItemClickListener {

        void getAllNotification();
    }
}
