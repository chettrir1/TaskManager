package com.techsales.taskmanager.dashboard;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.TopItems;
import com.techsales.taskmanager.data.model.TopRecyclerItems;
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

        TopRecyclerItems topRecyclerItems = new TopRecyclerItems();
        topRecyclerItems.setTaskCount("1");
        topRecyclerItems.setTaskName(component.context().getString(R.string.task_new));
        topRecyclerItems.setTaskIcon(icons[0]);
        topRecyclerItems.setTaskColor(color[0]);
        items.add(topRecyclerItems);

        topRecyclerItems.setTaskCount("2");
        topRecyclerItems.setTaskName(component.context().getString(R.string.task_open));
        topRecyclerItems.setTaskIcon(icons[1]);
        topRecyclerItems.setTaskColor(color[1]);
        items.add(topRecyclerItems);

        topRecyclerItems.setTaskCount("3");
        topRecyclerItems.setTaskName(component.context().getString(R.string.task_pending));
        topRecyclerItems.setTaskIcon(icons[2]);
        topRecyclerItems.setTaskColor(color[2]);
        items.add(topRecyclerItems);

        topRecyclerItems.setTaskCount("4");
        topRecyclerItems.setTaskName(component.context().getString(R.string.task_completed));
        topRecyclerItems.setTaskIcon(icons[3]);
        topRecyclerItems.setTaskColor(color[3]);
        items.add(topRecyclerItems);

        List<DashboardTopRecyclerViewModel> viewModel = TopItems.mapToViewModel(items);
        view.showTopRecyclerLoadSuccess(viewModel);
    }
}
