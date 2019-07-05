package com.techsales.taskmanager.status;

import android.util.Log;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.error.FailedResponseException;
import com.techsales.taskmanager.data.error.NetworkNotAvailableException;
import com.techsales.taskmanager.data.model.api.status.BaseStatusResponse;
import com.techsales.taskmanager.data.model.api.status.StatusResponse;
import com.techsales.taskmanager.data.model.viewmodel.status.StatusViewModel;
import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;

import java.util.List;

import io.reactivex.disposables.Disposable;

class StatusPresenter implements StatusContract.Presenter {
    private static final int LIMIT = 10;
    private final TaskManagerComponent component;
    private StatusContract.View view;
    private Disposable disposable;
    private int page;

    StatusPresenter(TaskManagerComponent component, StatusContract.View view) {
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
    public void onRecyclerItemClicked(StatusViewModel items, int position) {

    }

    @Override
    public void requestStatus() {
        view.showProgress();
        this.page = 1;
        disposable = component.data().requestStatus(page, LIMIT,
                component.data().savedUserInfo().getId(), view.getStatus())
                .subscribe(response -> {
                    int itemCount = response.getTotal();
                    List<StatusResponse> items = response.getItems();
                        boolean isTrue = Commons.isNotEmpty(items);
                    if (itemCount > 0 && isTrue) {
                        final int count = items.size();
                        page += count;
                        List<StatusViewModel> viewModel = BaseStatusResponse.mapToViewModel(component.context(), items);
                        view.showLoadingSuccess(viewModel, itemCount > page);
                    } else {
                        view.showLoadingError(component.context().getString(R.string.status_error_empty_task));
                    }
                }, throwable -> {
                    if (throwable instanceof FailedResponseException) {
                        view.showLoadingError(throwable.getMessage());
                    } else if (throwable instanceof NetworkNotAvailableException) {
                        view.showNoNetworkAvailableError();
                    } else {
                        view.showLoadingError(component.context().getString(R.string.error_server));
                    }

                });
    }

    @Override
    public void onLoadMore() {
        view.showLoadMoreProgress();
        disposable = component.data().requestStatus(page, LIMIT, component.data().savedUserInfo().getId(), view.getStatus())
                .subscribe(response -> {
                    if (response != null) {
                        int itemCount = response.getTotal();
                        List<StatusResponse> items = response.getItems();
                        Log.v("getItemsResponse", items + "");
                        if (itemCount > 0 && Commons.isNotEmpty(items)) {
                            final int count = items.size();
                            page += count;
                            List<StatusViewModel> viewModel = BaseStatusResponse.mapToViewModel(component.context(), items);
                            view.showMoreItems(viewModel, itemCount > page);
                        } else {
                            view.onLoadComplete();
                        }
                    } else {
                        view.showLoadMoreError();
                    }
                }, throwable -> view.showLoadMoreError());
    }


}
