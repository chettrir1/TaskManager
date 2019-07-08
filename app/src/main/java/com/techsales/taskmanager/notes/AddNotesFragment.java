package com.techsales.taskmanager.notes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.internal.Preconditions;
import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.FragmentAddNotesBinding;

import javax.inject.Inject;

public class AddNotesFragment extends BaseFragment implements AddNotesContract.View {

    public static Fragment getInstance() {
        return new AddNotesFragment();
    }

    @Inject
    AddNotesContract.Presenter presenter;

    private FragmentAddNotesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_notes, null, false);
        binding.includeToolbar.ivSave.setOnClickListener(view -> {
            presenter.insertNotes(binding.etTitle.getText().toString(), binding.etDescription.getText().toString());
        });


        binding.includeToolbar.ivBack.setOnClickListener(view -> {
        });

        return binding.getRoot();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showEmptyFields(String message) {
/*
        Commons.showSnackBar(component.context(), binding.coordinatorLayout, message);
*/
    }

    @Override
    public void showNoteAddSuccess() {
        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();

    }
}
