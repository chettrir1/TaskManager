package com.techsales.taskmanager.profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.profile.ProfileViewModel;
import com.techsales.taskmanager.databinding.FragmentProfileBinding;

import javax.inject.Inject;

public class ProfileFragment extends BaseFragment implements ProfileContract.View {

    @Inject
    ProfileContract.Presenter presenter;

    public static Fragment getInstance() {
        return new ProfileFragment();
    }

    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, null, false);
        setProfileData();
        return binding.getRoot();
    }

    private void setProfileData() {
        ProfileViewModel profileViewModel = presenter.getProfileViewModel();
        if (profileViewModel != null) {
            binding.setViewModel(profileViewModel);
        }
    }
}
