package com.techsales.taskmanager.contacts;


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
public class ContactsFragment extends BaseFragment implements ContactsContract.View {

    public static ContactsFragment getInstance() {
        return new ContactsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

}
