package com.techsales.taskmanager.notification.viewnotification;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.notification.NotificationDetails;
import com.techsales.taskmanager.data.model.viewmodel.notification.NotificationDetailsViewModel;
import com.techsales.taskmanager.databinding.FragmentViewNotificationBinding;

import javax.inject.Inject;

public class ViewNotificationFragment extends BaseFragment implements ViewNotificationContract.View {
    private static final String NOTIFICATION_DETAILS = "notificationDetails";

    private FragmentViewNotificationBinding binding;

    @Inject
    ViewNotificationContract.Presenter presenter;

    public static Fragment getInstance(NotificationDetails notificationDetails) {
        ViewNotificationFragment fragment = new ViewNotificationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(NOTIFICATION_DETAILS, notificationDetails);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_notification, null, false);
        getDataWithBundle();
        return binding.getRoot();
    }

    private void getDataWithBundle() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            NotificationDetails details = (NotificationDetails) bundle.getSerializable(NOTIFICATION_DETAILS);
            if (details != null) {
                NotificationDetailsViewModel viewModel = presenter.getNotificationDetailsViewModel(details);
                if (viewModel != null) {
                    binding.setNotificationDetails(viewModel);
                }
            }
        }
    }

}
