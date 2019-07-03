package com.techsales.taskmanager.createtask.chooseemployee;

import android.widget.CheckBox;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.error.FailedResponseException;
import com.techsales.taskmanager.data.error.NetworkNotAvailableException;
import com.techsales.taskmanager.data.model.api.chooseemployee.BaseChooseEmployeeResponse;
import com.techsales.taskmanager.data.model.api.chooseemployee.ChooseEmployeeResponse;
import com.techsales.taskmanager.data.model.viewmodel.chooseemployee.ChooseEmployeeViewModel;
import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;

import java.util.List;

import io.reactivex.disposables.Disposable;

class ChooseEmployeePresenter implements ChooseEmployeeContract.Presenter {

    private final TaskManagerComponent component;
    private ChooseEmployeeContract.View view;
    private Disposable disposable;

    ChooseEmployeePresenter(TaskManagerComponent component, ChooseEmployeeContract.View view) {
        this.component = component;
        this.view = view;
    }

    @Override
    public void start() {
        view.showProgress();
        disposable = component.data().getAllEmployee()
                .subscribe((List<ChooseEmployeeResponse> response) -> {
                    if (Commons.isEmpty(response)) {
                        List<ChooseEmployeeViewModel> viewModels =
                                BaseChooseEmployeeResponse.mapToViewModel(component.context(), response);
                        view.showLoadingSuccess(viewModels);
                    }
                }, throwable -> {
                    if (throwable instanceof FailedResponseException)
                        view.showLoadingError(throwable.getMessage());
                    else if (throwable instanceof NetworkNotAvailableException)
                        view.showNoNetworkAvailable();
                    else
                        view.showLoadingError(component.context().getString(R.string.error_server));
                });
    }

    @Override
    public void stop() {
        Commons.dispose(disposable);
    }

    @Override
    public void onChooseEmployeeItemClick(ChooseEmployeeViewModel items, int position, CheckBox cbAssignTo) {
        view.onEmployeeItemClick(items, position, cbAssignTo);
    }
}
