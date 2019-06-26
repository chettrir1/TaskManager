package com.techsales.taskmanager.notification;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.error.FailedResponseException;
import com.techsales.taskmanager.data.error.NetworkNotAvailableException;
import com.techsales.taskmanager.data.model.api.notification.NotificationResponse;
import com.techsales.taskmanager.data.model.viewmodel.notification.NotificationViewModel;
import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;

import io.reactivex.disposables.Disposable;

public class NotificationPresenter implements NotificationContract.Presenter {

    private final TaskManagerComponent component;
    private NotificationContract.View view;
    private Disposable disposable;

    NotificationPresenter(TaskManagerComponent component, NotificationContract.View view) {
        this.component = component;
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        Commons.dispose(disposable);

    }

    @Override
    public void getAllNotification(String userId) {
        disposable = component.data().getAllNotification(userId)
                .subscribe(notificationResponse -> {
                    if (notificationResponse != null) {
                        NotificationViewModel viewModel =
                                NotificationResponse.mapToViewModel(component.context(), notificationResponse);
                        view.showLoadingSuccess(viewModel);
                    } else {
                        view.showLoadingError(component.context().getString(R.string.data_not_available));
                    }
                }, throwable -> {
                    if (throwable instanceof FailedResponseException)
                        view.showLoadingError(throwable.getMessage());
                    else if (throwable instanceof NetworkNotAvailableException)
                        view.showNetworkNotAvailable();
                    else
                        view.showLoadingError(component.context().getString(R.string.server_error));
                });
    }
}
