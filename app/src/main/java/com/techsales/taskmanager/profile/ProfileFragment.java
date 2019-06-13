package com.techsales.taskmanager.profile;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment implements ProfileContract.View {


    public ProfileFragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance() {
        ProfileFragment profileFragment = new ProfileFragment();

        return profileFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void showProgress() {

    }
}
