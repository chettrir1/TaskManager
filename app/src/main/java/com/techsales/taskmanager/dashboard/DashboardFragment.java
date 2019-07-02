package com.techsales.taskmanager.dashboard;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.auth.login.LoginActivity;
import com.techsales.taskmanager.createtask.container.CreateNewTaskActivity;
import com.techsales.taskmanager.dashboard.viewtask.ViewTaskFragment;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardBottomRecyclerViewModel;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardTopRecyclerViewModel;
import com.techsales.taskmanager.databinding.FragmentDashboardBinding;
import com.techsales.taskmanager.status.container.StatusActivity;
import com.techsales.taskmanager.utils.GridSpacingItemDecoration;

import java.util.List;

import javax.inject.Inject;

public class DashboardFragment extends BaseFragment implements DashboardContract.View {

    private static final int SPACING_VALUE = 10;

    public static DashboardFragment getInstance() {
        return new DashboardFragment();
    }

    @Inject
    DashboardContract.Presenter presenter;

    private FragmentDashboardBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, null, false);
        binding.contentState.setContent(binding.content);
        binding.swipeContainer.setColorSchemeResources(R.color.colorBlue, R.color.colorYellow, R.color.colorRed, R.color.colorGreen);
        checkAlreadyLogin();
        initTopRecyclerView();
        binding.swipeContainer.setOnRefreshListener(() -> presenter.onBottomRecyclerLoad());
        initFabView();

        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
        presenter.onBottomRecyclerLoad();
    }

    @Override
    public void onPause() {
        presenter.stop();
        super.onPause();
    }

    private void initTopRecyclerView() {
        binding.dashboardTopRv.setLayoutManager(new GridLayoutManager(component.context(), 2));
        binding.dashboardTopRv.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(), true));
        binding.dashboardTopRv.setItemAnimator(new DefaultItemAnimator());
        binding.dashboardTopRv.setNestedScrollingEnabled(false);
    }

    @Override
    public void checkIfAdmin(boolean admin) {
        if (admin) {
            binding.fabAssignTask.show();
        } else {
            binding.fabAssignTask.hide();
        }
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
        binding.contentState.showError(R.drawable.no_internet, getString(R.string.error_network_not_available));
    }

    @Override
    public void onTopRecyclerItemClicked(DashboardTopRecyclerViewModel items, int position) {
        StatusActivity.start(getActivity(), items.getTaskCount());
    }

    @Override
    public void onBottomRecyclerItemClicked(DashboardBottomRecyclerViewModel bottomRecyclerViewModel, int position) {
        viewTaskDetails(bottomRecyclerViewModel);
    }

    private void checkAlreadyLogin() {
        boolean isLoggedIn = presenter.isLoggedIn();
        if (!isLoggedIn) {
            Activity activity = getActivity();
            if (activity != null) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        }
    }

    private int dpToPx() {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, SPACING_VALUE, r.getDisplayMetrics()));
    }

    private void hideSwipeContainer() {
        if (binding.swipeContainer.isRefreshing() && binding.swipeContainer != null) {
            binding.swipeContainer.setRefreshing(false);
        }
    }

    private void viewTaskDetails(DashboardBottomRecyclerViewModel viewModel) {

        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.dashboardContainer,
                            ViewTaskFragment.getInstance(viewModel.getTaskDetails()))
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void initFabView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.scrollView.setOnScrollChangeListener((NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) -> {
                if (scrollY > oldScrollY) {
                    binding.fabAssignTask.hide();
                } else {
                    binding.fabAssignTask.show();
                }
            });
        }

        binding.fabAssignTask.setOnClickListener(view -> CreateNewTaskActivity.start(getActivity()));
    }


}
