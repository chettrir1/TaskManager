package com.techsales.taskmanager.createtask.chooseemployee;

import android.util.Log;
import android.widget.CheckBox;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.error.FailedResponseException;
import com.techsales.taskmanager.data.error.NetworkNotAvailableException;
import com.techsales.taskmanager.data.model.api.BaseResponse;
import com.techsales.taskmanager.data.model.api.chooseemployee.BaseChooseEmployeeResponse;
import com.techsales.taskmanager.data.model.api.chooseemployee.ChooseEmployeeResponse;
import com.techsales.taskmanager.data.model.createtask.AboutTask;
import com.techsales.taskmanager.data.model.viewmodel.chooseemployee.ChooseEmployeeViewModel;
import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;
import com.techsales.taskmanager.utils.Constants;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.disposables.Disposable;

class ChooseEmployeePresenter implements ChooseEmployeeContract.Presenter {

    private final TaskManagerComponent component;
    private ChooseEmployeeContract.View view;
    private Disposable disposable;
    private String taskTitle;
    private int taskDeadline;
    private String clienPhone;
    private String clientName;
    private String taskType;
    private int taskPriority;
    private String taskDetails;

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
    public void onChooseEmployeeItemClick(ChooseEmployeeViewModel items, boolean isChecked) {
        view.onEmployeeItemClick(items, isChecked);
    }

    @Override
    public void getDataWithBundle(AboutTask aboutTask) {
        taskTitle = aboutTask.getTitle();
        taskPriority = aboutTask.getTaskPriority();
        clientName = aboutTask.getClientName();
        taskDeadline = Integer.parseInt(aboutTask.getDeadline());
        taskType = aboutTask.getTaskType();
        clienPhone = aboutTask.getClientPhone();
        taskDetails = aboutTask.getTaskDetails();
    }

    @Override
    public void assignTask(JSONObject staffs) {
        view.showTaskAssignProgress();
        int userId = Integer.parseInt(component.data().savedUserInfo().getId());
        int status = Integer.parseInt(String.valueOf(Constants.COUNT_ZERO));
        disposable = component.data().assignTask(taskTitle, status,
                taskPriority, clientName, taskDeadline, staffs, userId, taskDetails)
                .subscribe((baseResponse) -> {
                    if (!baseResponse.getError())
                    view.showTaskAssignSuccess(baseResponse.getMessage());
                }, throwable -> {
                    if (throwable instanceof FailedResponseException)
                        view.showTaskAssignError(throwable.getMessage());
                    else if (throwable instanceof NetworkNotAvailableException)
                        view.showTaskAssignNetworkError();
                    else
                        view.showTaskAssignError(component.context().getString(R.string.error_server));
                });
    }

}
