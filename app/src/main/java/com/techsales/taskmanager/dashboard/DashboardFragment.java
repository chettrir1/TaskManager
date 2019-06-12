package com.techsales.taskmanager.dashboard;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.auth.login.LoginActivity;
import com.techsales.taskmanager.data.model.dashboard.top.TopItems;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardBottomRecyclerViewModel;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardTopRecyclerViewModel;
import com.techsales.taskmanager.databinding.FragmentDashboardBinding;
import com.techsales.taskmanager.utils.GridSpacingItemDecoration;

import java.util.List;

import javax.inject.Inject;

public class DashboardFragment extends BaseFragment implements DashboardContract.View, TopRecyclerAdapter.TopRecyclerItemClickListener {


    public static DashboardFragment getInstance() {
        DashboardFragment dashboardFragment = new DashboardFragment();
        return dashboardFragment;
    }

    @Inject
    DashboardContract.Presenter presenter;

    private FragmentDashboardBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, null, false);
        checkAlreadyLogin();
        initRecyclerView();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        presenter.start();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.stop();
        super.onPause();
    }


    private void initRecyclerView() {
        binding.dashboardTopRv.setLayoutManager(new GridLayoutManager(component.context(), 2));
        binding.dashboardTopRv.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        binding.dashboardTopRv.setItemAnimator(new DefaultItemAnimator());
        binding.dashboardTopRv.setNestedScrollingEnabled(false);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showTopRecyclerLoadSuccess(List<DashboardTopRecyclerViewModel> items) {

        TopRecyclerAdapter adapter = new TopRecyclerAdapter(items, this);
        binding.dashboardTopRv.setAdapter(adapter);
    }

    @Override
    public void showTasksLoadError(String message) {

    }

    @Override
    public void showEmptyTasks(String message) {

    }

    @Override
    public void showMoreTasks(List<DashboardBottomRecyclerViewModel> items, boolean hasMoreItems) {

    }

    @Override
    public void showLoadMoreError() {

    }

    @Override
    public void showNetworkNotAvailableError() {


    }

    @Override
    public void onTopRecyclerItemClicked(DashboardTopRecyclerViewModel dashboardTopRecyclerViewModel, int position) {

    }

    @Override
    public void onBottomRecyclerItemClicked(DashboardBottomRecyclerViewModel dashboardBottomRecyclerViewModel, int position) {

    }

    @Override
    public void onLoadComplete() {

    }


    private void checkAlreadyLogin() {
        boolean isLoggedIn = data.isLoggedIn();
        if (!isLoggedIn) {
            Activity activity = getActivity();
            if (activity != null) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
