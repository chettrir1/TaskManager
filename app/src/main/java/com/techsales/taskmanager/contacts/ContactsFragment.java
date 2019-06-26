package com.techsales.taskmanager.contacts;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.FragmentContactsBinding;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends BaseFragment implements ContactsContract.View {
    private FragmentContactsBinding binding;

    @Inject
    ContactsContract.Presenter presenter;


    public static ContactsFragment getInstance() {
        return new ContactsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contacts, null, false);
        binding.contentState.setContent(binding.content);

        return binding.getRoot();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showLoadingError(String message) {

    }

    @Override
    public void showLoadingSuccess() {

    }

    @Override
    public void showNoNetworkAvailable() {

    }
}
