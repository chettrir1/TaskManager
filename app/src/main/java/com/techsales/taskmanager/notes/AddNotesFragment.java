package com.techsales.taskmanager.notes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.techsales.taskmanager.R;

public class AddNotesFragment extends Fragment implements AddNotesContract.View {


    public static Fragment getInstance() {
        AddNotesFragment fragment = new AddNotesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_notes, container, false);
    }

}
