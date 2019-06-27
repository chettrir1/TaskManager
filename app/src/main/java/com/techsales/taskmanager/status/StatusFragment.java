package com.techsales.taskmanager.status;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.FragmentStatusBinding;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends BaseFragment implements StatusContract.View {
    private FragmentStatusBinding binding;

    @Inject
    StatusContract.Presenter presenter;

    public static Fragment getInstance() {
        return new StatusFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_status, null, false);
        binding.contentState.setContent(binding.content);
        binding.swipeContainer.setColorSchemeResources(R.color.colorBlue, R.color.colorYellow, R.color.colorRed, R.color.colorGreen);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
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
    public void showLoadingSuccess() {

    }

    @Override
    public void showNoNetworkAvailableError() {
        hideSwipeContainer();
        binding.contentState.showError(R.drawable.no_internet, getString(R.string.network_not_available_error));

    }

    private void hideSwipeContainer() {
        if (binding.swipeContainer.isRefreshing() && binding.swipeContainer != null) {
            binding.swipeContainer.setRefreshing(false);
        }
    }
}
