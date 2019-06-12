package com.techsales.taskmanager.dashboard;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.dashboard.top.TopItems;
import com.techsales.taskmanager.data.model.dashboard.top.TopRecyclerItems;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardBottomRecyclerViewModel;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardTopRecyclerViewModel;
import com.techsales.taskmanager.di.TaskManagerComponent;

import java.util.ArrayList;
import java.util.List;

public class DashboardPresenter implements DashboardContract.Presenter {
    private final TaskManagerComponent component;
    private DashboardContract.View view;


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

    }


    @Override
    public void onBottomRecyclerLoad(String userId) {

    }

    private void prepareDashBoardStatus() {
        List<TopRecyclerItems> items = new ArrayList<>();
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

        TopRecyclerItems newTask = new TopRecyclerItems("1", component.context().getString(R.string.task_new),
                icons[0], color[0]);
        items.add(newTask);

        TopRecyclerItems openTask = new TopRecyclerItems("2", component.context().getString(R.string.task_open),
                icons[1], color[1]);
        items.add(openTask);

        TopRecyclerItems pendingTask = new TopRecyclerItems("3", component.context().getString(R.string.task_pending),
                icons[2], color[2]);
        items.add(pendingTask);

        TopRecyclerItems completedTask = new TopRecyclerItems("4", component.context().getString(R.string.task_completed),
                icons[3], color[3]);
        items.add(completedTask);

        List<DashboardTopRecyclerViewModel> viewModel = TopItems.mapToViewModel(component.context(), items);
        view.showTopRecyclerLoadSuccess(viewModel);
    }

    @Override
    public void onBottomRecyclerItemClicked(DashboardBottomRecyclerViewModel items, int position) {
        view.onBottomRecyclerItemClicked(items, position);
    }

    @Override
    public void onLoadMore() {

    }


    @Override
    public void onTopRecyclerItemClicked(DashboardTopRecyclerViewModel items, int position) {

    }
}
