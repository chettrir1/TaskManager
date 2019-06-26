package com.techsales.taskmanager.notification;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.notification.NotificationViewModel;
import com.techsales.taskmanager.databinding.FragmentNotificationBinding;
import com.techsales.taskmanager.notification.viewnotification.ViewNotificationFragment;

import java.util.List;

import javax.inject.Inject;

import static android.widget.LinearLayout.VERTICAL;

public class NotificationFragment extends BaseFragment implements NotificationContract.View {

    @Inject
    NotificationContract.Presenter presenter;

    private FragmentNotificationBinding binding;

    public static Fragment getInstance() {
        return new NotificationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, null, false);
        binding.contentState.setContent(binding.content);
        binding.swipeContainer.setColorSchemeResources(R.color.colorBlue, R.color.colorYellow, R.color.colorRed, R.color.colorGreen);
        binding.swipeContainer.setOnRefreshListener(() -> presenter.getAllNotification());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        presenter.getAllNotification();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.stop();
        super.onPause();
    }

    @Override
    public void showProgress() {
        binding.contentState.showProgress(getResources().getString(R.string.text_please_wait));
    }

    @Override
    public void showLoadingError(String message) {
        hideSwipeContainer();
        binding.contentState.showError(R.drawable.ic_loading_error, message);
    }

    @Override
    public void showLoadingSuccess(List<NotificationViewModel> viewModel) {
        hideSwipeContainer();
        if (getContext() != null) {
            DividerItemDecoration decoration = new DividerItemDecoration(getContext(), VERTICAL);
            binding.rvNotification.addItemDecoration(decoration);
        }
        NotificationRecyclerAdapter adapter = new NotificationRecyclerAdapter(viewModel, presenter);
        binding.rvNotification.setAdapter(adapter);
        binding.contentState.showContent();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showNetworkNotAvailable() {
        hideSwipeContainer();
        binding.contentState.showError(R.drawable.no_internet, getString(R.string.network_not_available_error));
    }

    @Override
    public void onRecyclerItemClicked(NotificationViewModel viewModel, int position) {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.notificationContainer,
                            ViewNotificationFragment.getInstance(viewModel.getNotificationDetails()))
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void hideSwipeContainer() {
        if (binding.swipeContainer.isRefreshing() && binding.swipeContainer != null) {
            binding.swipeContainer.setRefreshing(false);
        }
    }
}
