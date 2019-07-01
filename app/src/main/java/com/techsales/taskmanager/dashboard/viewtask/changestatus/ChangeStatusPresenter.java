package com.techsales.taskmanager.dashboard.viewtask.changestatus;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.error.FailedResponseException;
import com.techsales.taskmanager.data.error.NetworkNotAvailableException;
import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;

import io.reactivex.disposables.Disposable;

class ChangeStatusPresenter implements ChangeStatusContract.Presenter {
    private final TaskManagerComponent component;
    private ChangeStatusContract.View view;
    private Disposable disposable;

    ChangeStatusPresenter(TaskManagerComponent component, ChangeStatusContract.View view) {
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
    public void changeStatus(String task_id, String status, String remarks) {
        disposable = component.data().requestChangeStatus(task_id, status, remarks)
                .subscribe(baseResponse -> view.showChangeStatusSuccess(baseResponse.getMessage()), throwable -> {
                    if (throwable instanceof FailedResponseException)
                        view.showChangeStatusError(throwable.getMessage());
                    else if (throwable instanceof NetworkNotAvailableException)
                        view.showNetworkNotAvailableError();
                    else
                        view.showChangeStatusError(component.context().getString(R.string.error_server));
                });

    }
}
