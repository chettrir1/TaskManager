package com.techsales.taskmanager.notes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.FragmentAddNotesBinding;

public class AddNotesFragment extends Fragment implements AddNotesContract.View {

    private FragmentAddNotesBinding binding;

    public static Fragment getInstance() {
        AddNotesFragment fragment = new AddNotesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_notes, null, false);

        return binding.getRoot();
    }

}
