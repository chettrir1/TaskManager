package com.techsales.taskmanager.dashboard.container;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.error.FailedResponseException;
import com.techsales.taskmanager.data.error.NetworkNotAvailableException;
import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;
import com.techsales.taskmanager.utils.Constants;

import io.reactivex.disposables.Disposable;

public class DashboardPresenter implements DashboardContract.Presenter {

    private final TaskManagerComponent component;
    private DashboardContract.View view;

    private Disposable disposable;

    DashboardPresenter(TaskManagerComponent component, DashboardContract.View view) {
        this.component = component;
        this.view = view;
    }

    @Override
    public void start() {
        String type = component.data().savedUserInfo().getUserType();
        if (type.equals(String.valueOf(Constants.COUNT_ONE))) {
            String admin = component.context().getString(R.string.dashboard_user_type_administrator);
            view.setToolbarTitle(admin);
        } else {
            String officeStaff = component.context().getString(R.string.dashboard_user_type_office_staff);
            view.setToolbarTitle(officeStaff);
        }
    }

    @Override
    public void stop() {
        Commons.dispose(disposable);
    }

    @Override
    public void onLogout() {
        view.showProgress();
        disposable = component.data().requestLogout(component.data().savedUserInfo().getId())
                .subscribe(baseResponse -> {
                    component.data().logout();
                    view.onLogoutSelected();
                }, throwable -> {
                    if (throwable instanceof FailedResponseException)
                        view.showError(throwable.getMessage());
                    else if (throwable instanceof NetworkNotAvailableException)
                        view.showNetworkNotAvailableError(component.context()
                                .getString(R.string.error_network_not_available));
                    else
                        view.showError(component.context()
                                .getString(R.string.error_server));
                });
    }
}
