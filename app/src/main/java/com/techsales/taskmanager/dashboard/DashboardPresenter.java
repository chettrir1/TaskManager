package com.techsales.taskmanager.dashboard;

import android.util.Log;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.error.FailedResponseException;
import com.techsales.taskmanager.data.error.NetworkNotAvailableException;
import com.techsales.taskmanager.data.model.api.dashboard.BaseTasksResponse;
import com.techsales.taskmanager.data.model.api.dashboard.WhereTask;
import com.techsales.taskmanager.data.model.dashboard.taskcount.TaskCount;
import com.techsales.taskmanager.data.model.dashboard.top.Status;
import com.techsales.taskmanager.data.model.dashboard.top.TaskStatus;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardBottomRecyclerViewModel;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardTopRecyclerViewModel;
import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;
import com.techsales.taskmanager.utils.Constants;

import java.util.ArrayList;
import java.util.List;

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
        checkIfAdminOrNot();
        getTaskCount();
    }

    @Override
    public void stop() {
        Commons.dispose(disposable);
    }

    @Override
    public void onBottomRecyclerLoad() {
        loadAllTask();
    }

    @Override
    public boolean isLoggedIn() {
        return component.data().isLoggedIn();
    }

    @Override
    public void onTopRecyclerItemClicked(DashboardTopRecyclerViewModel items, int position) {
        view.onTopRecyclerItemClicked(items, position);
    }

    private void prepareDashBoardStatus(TaskCount taskCount) {
        List<TaskStatus> items = new ArrayList<>();
        int[] icons = new int[]{
                R.drawable.new_task_src,
                R.drawable.opened_task_src,
                R.drawable.pending_task_src,
                R.drawable.completed_task_src
        };

        int[] color = new int[]{
                R.color.colorBlue,
                R.color.colorYellow,
                R.color.colorRed,
                R.color.colorGreen
        };

        TaskStatus newTask = new TaskStatus(taskCount.getTaskNew(), component.context().getString(R.string.dashboard_task_text_new),
                icons[0], color[0]);
        items.add(newTask);

        TaskStatus openTask = new TaskStatus(taskCount.getTaskOpened(), component.context().getString(R.string.dashboard_task_text_open),
                icons[1], color[1]);
        items.add(openTask);

        TaskStatus pendingTask = new TaskStatus(taskCount.getTaskPending(), component.context().getString(R.string.dashboard_task_text_pending),
                icons[2], color[2]);
        items.add(pendingTask);

        TaskStatus completedTask = new TaskStatus(taskCount.getTaskCompleted(), component.context().getString(R.string.dashboard_task_text_complete),
                icons[3], color[3]);
        items.add(completedTask);

        List<DashboardTopRecyclerViewModel> viewModel = Status.mapToViewModel(component.context(), items);
        view.showTopRecyclerLoadSuccess(viewModel);
    }

    private void getTaskCount() {
        disposable = component.data().getTaskCount(component.data().savedUserInfo().getId())
                .subscribe(taskCount -> {
                    if (taskCount != null) {
                        prepareDashBoardStatus(taskCount);
                    }
                }, throwable -> {
                    if (throwable instanceof FailedResponseException) {
                        view.showTasksLoadError(throwable.getMessage());
                    } else if (throwable instanceof NetworkNotAvailableException) {
                        view.showNetworkNotAvailableError();
                    } else {
                        view.showTasksLoadError(component.context().getString(R.string.error_server));
                    }
                });
    }

    private void loadAllTask() {
        view.showProgress();
        disposable = component.data().getAllTasks(component.data().savedUserInfo().getId())
                .subscribe((List<WhereTask> whereTasksList) -> {
                    if (Commons.isNotEmpty(whereTasksList)) {
                        List<DashboardBottomRecyclerViewModel> viewModels = BaseTasksResponse.mapToViewModel(component.context(), whereTasksList);
                        view.showBottomRecyclerLoadSuccess(viewModels);
                    } else {
                        view.showEmptyTasks(component.context().getString(R.string.dashboard_error_empty_task));
                    }
                }, throwable -> {
                    if (throwable instanceof FailedResponseException) {
                        view.showTasksLoadError(throwable.getMessage());
                    } else if (throwable instanceof NetworkNotAvailableException) {
                        view.showNetworkNotAvailableError();
                    } else {
                        view.showTasksLoadError(component.context().getString(R.string.error_server));
                    }
                });
    }

    @Override
    public void onBottomRecyclerItemClicked(DashboardBottomRecyclerViewModel items, int position) {
        view.onBottomRecyclerItemClicked(items, position);
    }

    private void checkIfAdminOrNot() {
        String ifAdmin = component.data().savedUserInfo().getUserType();
        if (ifAdmin.equals(Constants.USER_ADMIN)) {
            view.checkIfAdmin(true);
        } else {
            view.checkIfAdmin(false);
        }
    }

}
