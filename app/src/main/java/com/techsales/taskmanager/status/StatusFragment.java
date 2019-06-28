package com.techsales.taskmanager.status;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.status.StatusViewModel;
import com.techsales.taskmanager.databinding.FragmentStatusBinding;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends BaseFragment implements StatusContract.View {
    private static final String STATUS = "status";
    private FragmentStatusBinding binding;
    private int status;

    @Inject
    StatusContract.Presenter presenter;

    public static Fragment getInstance(String status) {
        StatusFragment fragment = new StatusFragment();
        Bundle bundle = new Bundle();
        bundle.putString(STATUS, status);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(@Nonnull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_status, null, false);
        binding.contentState.setContent(binding.content);
        getDataWithBundle();
        binding.swipeContainer.setColorSchemeResources(R.color.colorBlue, R.color.colorYellow, R.color.colorRed, R.color.colorGreen);
        binding.swipeContainer.setOnRefreshListener(() -> presenter.requestStatus(status));
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        presenter.requestStatus(status);
        super.onResume();
    }

    @Override
    public void onStop() {
        presenter.stop();
        super.onStop();
    }

    @Override
    public void showProgress() {
        hideSwipeContainer();
        binding.contentState.showProgress(getResources().getString(R.string.text_please_wait));
    }

    @Override
    public void showLoadingError(String message) {
        hideSwipeContainer();
        binding.contentState.showError(R.drawable.ic_loading_error, message);
    }

    @Override
    public void showLoadingSuccess(List<StatusViewModel> viewModel, boolean hasMoreItems) {
        StatusRecyclerAdapter adapter = (StatusRecyclerAdapter) binding.rvStatus.getAdapter();
        if (adapter != null)
            adapter.detachLoadMore();
        adapter = new StatusRecyclerAdapter(binding.rvStatus, viewModel, presenter);
        if (hasMoreItems)
            adapter.attachLoadMore(presenter, status);
        binding.rvStatus.setAdapter(adapter);
        binding.contentState.showContent();
    }

    @Override
    public void showLoadMoreProgress() {

    }

    @Override
    public void showMoreTags(List<StatusViewModel> items, boolean hasMoreItems) {
        StatusRecyclerAdapter adapter = (StatusRecyclerAdapter) binding.rvStatus.getAdapter();
        if (adapter != null)
            adapter.addMoreItems(items, hasMoreItems);
    }

    @Override
    public void showNoNetworkAvailableError() {
        hideSwipeContainer();
        binding.contentState.showError(R.drawable.no_internet, getString(R.string.network_not_available_error));

    }

    @Override
    public void showLoadMoreError() {
        StatusRecyclerAdapter adapter = (StatusRecyclerAdapter) binding.rvStatus.getAdapter();
        if (adapter != null)
            adapter.onLoadError();
    }

    @Override
    public void onLoadComplete() {
        StatusRecyclerAdapter adapter = (StatusRecyclerAdapter) binding.rvStatus.getAdapter();
        if (adapter != null)
            adapter.onLoadComplete();
    }

    @Override
    public void onStatusItemClicked(StatusViewModel items, int position) {

    }

    private void hideSwipeContainer() {
        if (binding.swipeContainer.isRefreshing() && binding.swipeContainer != null) {
            binding.swipeContainer.setRefreshing(false);
        }
    }

    private void getDataWithBundle() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            status = Integer.parseInt(bundle.getString(STATUS));
        }
    }
}
