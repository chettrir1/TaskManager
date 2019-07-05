package com.techsales.taskmanager.status;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;
import com.techsales.taskmanager.data.model.viewmodel.status.StatusViewModel;
import com.techsales.taskmanager.views.LoadMoreAdapter;

import java.util.List;

public interface StatusContract {
    interface View extends BaseView<Presenter> {
        Integer getStatus();
        void showProgress();

        void showLoadingError(String message);

        void showLoadingSuccess(List<StatusViewModel> items, boolean hasMoreItems);

        void showNoNetworkAvailableError();

        void showLoadMoreProgress();

        void showMoreItems(List<StatusViewModel> items, boolean hasMoreItems);

        void showLoadMoreError();

        void onLoadComplete();

        void onStatusItemClicked(StatusViewModel items, int position);
    }

    interface Presenter extends BasePresenter,
            LoadMoreAdapter.LoadMoreListener,
            StatusRecyclerAdapter.StatusItemClickListener {
        void requestStatus();
    }
}
