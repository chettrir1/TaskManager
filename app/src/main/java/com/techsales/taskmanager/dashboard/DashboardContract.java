package com.techsales.taskmanager.dashboard;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardTopRecyclerViewModel;
import com.techsales.taskmanager.views.LoadMoreAdapter;

import java.util.List;

public interface DashboardContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void showTopRecyclerLoadSuccess(List<DashboardTopRecyclerViewModel> items);

        void showNetworkNotAvailableError();

        void onTopRecyclerItemClicked(DashboardTopRecyclerViewModel dashboardTopRecyclerViewModel, int position);

        void onLoadComplete();
    }

    interface Presenter extends BasePresenter, LoadMoreAdapter.LoadMoreListener, TopRecyclerAdapter.RecyclerItemClickListener {

        void onTopRecyclerLoad(String newCount, String openCount, String pendingCount, String completedCount);

        void onBottomRecyclerLoad(String userId);
    }
}
