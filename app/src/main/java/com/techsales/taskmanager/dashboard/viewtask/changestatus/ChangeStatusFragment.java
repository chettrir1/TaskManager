package com.techsales.taskmanager.dashboard.viewtask.changestatus;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.techsales.taskmanager.BaseDialog;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.FragmentChangeStatusBinding;


public class ChangeStatusFragment extends BaseDialog {
    private static final String STATUS_NAME = "statusName";

    public static ChangeStatusFragment getInstance(String name) {
        ChangeStatusFragment fragment = new ChangeStatusFragment();
        Bundle bundle = new Bundle();
        bundle.putString(STATUS_NAME, name);
        fragment.setArguments(bundle);
        return fragment;
    }

    private FragmentChangeStatusBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_status, null, false);
        Bundle bundle = getArguments();
        if (bundle != null)
            binding.tvStatusName.setText(bundle.getString(STATUS_NAME));

        return binding.getRoot();
    }
}
