package com.techsales.taskmanager.notification;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.notification.NotificationViewModel;

public class NotificationFragment extends BaseFragment implements NotificationContract.View {

    public static Fragment getInstance() {
        return new NotificationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showLoadingError(String message) {

    }

    @Override
    public void showLoadingSuccess(NotificationViewModel viewModel) {

    }

    @Override
    public void showNetworkNotAvailable() {

    }
}
