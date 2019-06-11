package com.techsales.taskmanager.dashboard;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.auth.login.LoginActivity;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardTopRecyclerViewModel;
import com.techsales.taskmanager.databinding.FragmentDashboardBinding;

import java.util.List;

import javax.inject.Inject;

public class DashboardFragment extends BaseFragment implements DashboardContract.View {

    public static DashboardFragment getInstance() {
        DashboardFragment   dashboardFragment = new DashboardFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, null, false);

        checkAlreadyLogin();

        binding.contentState.setContent(binding.content);
        presenter.onTopRecyclerLoad("1", "2", "3", "4");
        return binding.getRoot();
    }

    @Override
    public void showProgress() {
        binding.contentState.showProgress("Please wait..");
    }

    @Override
    public void showTopRecyclerLoadSuccess(List<DashboardTopRecyclerViewModel> items) {
        TopRecyclerAdapter adapter = (TopRecyclerAdapter) binding.dashboardTopRv.getAdapter();
        if (adapter != null) {
            adapter = new TopRecyclerAdapter(binding.dashboardTopRv, items, presenter);
            binding.dashboardTopRv.setAdapter(adapter);
            binding.contentState.showContent();
        }

    }

    @Override
    public void showNetworkNotAvailableError() {
        binding.contentState.showError(R.drawable.ic_loading_error, getString(R.string.network_not_available_error));

    }

    @Override
    public void onTopRecyclerItemClicked(DashboardTopRecyclerViewModel dashboardTopRecyclerViewModel, int position) {

    }

    @Override
    public void onLoadComplete() {
        TopRecyclerAdapter adapter = (TopRecyclerAdapter) binding.dashboardTopRv.getAdapter();
        if (adapter != null) {
            adapter.onLoadComplete();
        }
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

    private void checkAlreadyLogin() {
        boolean isLoggedIn = data.isLoggedIn();
        if (isLoggedIn) {
            Activity activity = getActivity();
            if (activity != null) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        }
    }
}
