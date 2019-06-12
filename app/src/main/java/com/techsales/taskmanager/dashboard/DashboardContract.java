package com.techsales.taskmanager.dashboard;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardBottomRecyclerViewModel;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardTopRecyclerViewModel;
import com.techsales.taskmanager.views.LoadMoreAdapter;

import java.util.List;

public interface DashboardContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void showTopRecyclerLoadSuccess(List<DashboardTopRecyclerViewModel> items);

        void showTasksLoadError(String message);

        void showEmptyTasks(String message);

        void showMoreTasks(List<DashboardBottomRecyclerViewModel> items, boolean hasMoreItems);

        void showLoadMoreError();

        void onLoadComplete();

        void showNetworkNotAvailableError();

        void onTopRecyclerItemClicked(DashboardTopRecyclerViewModel dashboardTopRecyclerViewModel, int position);

        void onBottomRecyclerItemClicked(DashboardBottomRecyclerViewModel dashboardBottomRecyclerViewModel, int position);

    }

    interface Presenter extends BasePresenter,
            LoadMoreAdapter.LoadMoreListener,
            TopRecyclerAdapter.TopRecyclerItemClickListener,
            BottomRecyclerAdapter.BottomRecyclerItemClickListener {

        void onBottomRecyclerLoad(String userId);
    }
}