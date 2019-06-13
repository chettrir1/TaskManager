package com.techsales.taskmanager.profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.UserInfo;
import com.techsales.taskmanager.databinding.FragmentProfileBinding;

public class ProfileFragment extends BaseFragment implements ProfileContract.View {

    private static final String IMAGE_URL = "http://117.121.237.226:83/task/public/storage/";

    public static Fragment getInstance() {
        ProfileFragment profileFragment = new ProfileFragment();
        return profileFragment;
    }

    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
        if (data.savedUserInfo() != null) {
            UserInfo info = data.savedUserInfo();
            Glide.with(this).load(IMAGE_URL + info.getProfile_image()).into(binding.ivProfile);

            binding.details.tvName.setText(info.getFull_name());
            binding.details.tvUsername.setText(info.getUser_name());
            binding.details.tvEmail.setText(info.getEmail());
            binding.details.tvPermanent.setText(info.getPermanent_address());
            binding.details.tvTemporary.setText(info.getTemporary_address());
            binding.details.tvContact.setText(info.getContact());
        }
    }
}
