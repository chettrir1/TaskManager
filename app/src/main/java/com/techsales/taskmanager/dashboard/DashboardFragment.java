package com.techsales.taskmanager.dashboard;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.auth.login.LoginActivity;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardBottomRecyclerViewModel;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardTopRecyclerViewModel;
import com.techsales.taskmanager.databinding.FragmentDashboardBinding;
import com.techsales.taskmanager.utils.GridSpacingItemDecoration;

import java.util.List;

import javax.inject.Inject;

public class DashboardFragment extends BaseFragment implements DashboardContract.View {


    public static DashboardFragment getInstance() {
        DashboardFragment dashboardFragment = new DashboardFragment();
        return dashboardFragment;
    }

    @Inject
    DashboardContract.Presenter presenter;

    private FragmentDashboardBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, null, false);
        checkAlreadyLogin();
        initTopRecyclerView();
        binding.contentState.setContent(binding.content);
        getBottomRecyclerData();
        binding.swipeContainer.setColorScheme(R.color.colorBlue, R.color.colorYellow, R.color.colorRed, R.color.colorGreen);
        binding.swipeContainer.setOnRefreshListener(this::getBottomRecyclerData);
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

    private void initTopRecyclerView() {
        binding.dashboardTopRv.setLayoutManager(new GridLayoutManager(component.context(), 2));
        binding.dashboardTopRv.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        binding.dashboardTopRv.setItemAnimator(new DefaultItemAnimator());
        binding.dashboardTopRv.setNestedScrollingEnabled(false);
    }

    @Override
    public void showProgress() {
        binding.contentState.showProgress();
    }

    @Override
    public void showTopRecyclerLoadSuccess(List<DashboardTopRecyclerViewModel> items) {
        hideSwipeContainer();
        TopRecyclerAdapter adapter = new TopRecyclerAdapter(items, presenter);
        binding.dashboardTopRv.setAdapter(adapter);
    }

    @Override
    public void showBottomRecyclerLoadSuccess(List<DashboardBottomRecyclerViewModel> items) {
        hideSwipeContainer();
        Toast.makeText(component.context(), items.get(0).getName(), Toast.LENGTH_SHORT).show();
        BottomRecyclerAdapter adapter = new BottomRecyclerAdapter(items, presenter);
        binding.dashboardBottomRv.setAdapter(adapter);
        binding.contentState.showContent();
    }

    @Override
    public void showTasksLoadError(String message) {
        hideSwipeContainer();
        binding.contentState.showError(R.drawable.server_error, message);

    }

    @Override
    public void showEmptyTasks(String message) {
        hideSwipeContainer();
        binding.contentState.showError(R.drawable.empty_src, message);

    }

    @Override
    public void showNetworkNotAvailableError() {
        hideSwipeContainer();
        binding.contentState.showError(R.drawable.no_internet, getString(R.string.network_not_available_error));
    }

    @Override
    public void onTopRecyclerItemClicked(DashboardTopRecyclerViewModel items, int position) {
    }

    @Override
    public void onBottomRecyclerItemClicked(DashboardBottomRecyclerViewModel bottomRecyclerViewModel, int position) {

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

    private void getBottomRecyclerData() {
        if (data.savedUserInfo() != null) {
            String user_id = data.savedUserInfo().getId();
            presenter.onBottomRecyclerLoad(user_id);
        }
    }

    private void hideSwipeContainer() {
        if (binding.swipeContainer.isRefreshing() && binding.swipeContainer != null) {
            binding.swipeContainer.setRefreshing(false);
        }
    }
}
