package com.techsales.taskmanager.dashboard;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.error.FailedResponseException;
import com.techsales.taskmanager.data.error.NetworkNotAvailableException;
import com.techsales.taskmanager.data.model.api.dashboard.BaseTasksResponse;
import com.techsales.taskmanager.data.model.api.dashboard.WhereTask;
import com.techsales.taskmanager.data.model.dashboard.top.Status;
import com.techsales.taskmanager.data.model.dashboard.top.TaskStatus;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardBottomRecyclerViewModel;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardTopRecyclerViewModel;
import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;

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
        prepareDashBoardStatus();
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

    private void prepareDashBoardStatus() {
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

        TaskStatus newTask = new TaskStatus("1", component.context().getString(R.string.task_new),
                icons[0], color[0]);
        items.add(newTask);

        TaskStatus openTask = new TaskStatus("2", component.context().getString(R.string.task_open),
                icons[1], color[1]);
        items.add(openTask);

        TaskStatus pendingTask = new TaskStatus("3", component.context().getString(R.string.task_pending),
                icons[2], color[2]);
        items.add(pendingTask);

        TaskStatus completedTask = new TaskStatus("4", component.context().getString(R.string.task_completed),
                icons[3], color[3]);
        items.add(completedTask);

        List<DashboardTopRecyclerViewModel> viewModel = Status.mapToViewModel(component.context(), items);
        view.showTopRecyclerLoadSuccess(viewModel);
    }

    private void loadAllTask() {
        view.showProgress();
        disposable = component.data().getAllTasks(component.data().savedUserInfo().getId())
                .subscribe((List<WhereTask> whereTasksList) -> {
//                    int itemCount = tasks.getItemCount();
                    if (!Commons.isEmpty(whereTasksList)) {
                        List<DashboardBottomRecyclerViewModel> viewModels = BaseTasksResponse.mapToViewModel(component.context(), whereTasksList);
                        view.showBottomRecyclerLoadSuccess(viewModels);
                    } else {
                        view.showEmptyTasks(component.context().getString(R.string.data_not_available));
                    }
                }, throwable -> {
                    if (throwable instanceof FailedResponseException) {
                        view.showTasksLoadError(throwable.getMessage());
                    } else if (throwable instanceof NetworkNotAvailableException) {
                        view.showNetworkNotAvailableError();
                    } else {
                        view.showTasksLoadError(component.context().getString(R.string.server_error));
                    }
                });

    }

    @Override
    public void onBottomRecyclerItemClicked(DashboardBottomRecyclerViewModel items, int position) {
        view.onBottomRecyclerItemClicked(items, position);

    }

}
