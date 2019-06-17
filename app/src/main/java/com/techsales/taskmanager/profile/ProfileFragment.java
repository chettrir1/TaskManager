package com.techsales.taskmanager.profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.profile.ProfileViewModel;
import com.techsales.taskmanager.databinding.FragmentProfileBinding;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

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

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showDataLoadSuccess() {

    }

    @Override
    public void hideProgress() {

    }

    private void setProfileData() {

        ProfileViewModel profileViewModel = presenter.getProfileViewModel();
        if (profileViewModel != null){
            binding.setViewModel(profileViewModel);
        }

//        set all the text from fragment_profile.xml and remove all the comments from here


//        if (data.savedUserInfo() != null) {
//            UserInfo info = data.savedUserInfo();
//            Glide.with(this).load(IMAGE_URL + info.getProfileImage()).into(binding.ivProfile);

//            binding.details.tvName.setText(info.getFullName());
//            binding.details.tvUsername.setText(info.getUserName());
//            binding.details.tvEmail.setText(info.getEmail());
//            binding.details.tvPermanent.setText(info.getPermanentAddress());
//            binding.details.tvTemporary.setText(info.getTemporaryAddress());
//            binding.details.tvContact.setText(info.getContact());
//        }
    }
}
