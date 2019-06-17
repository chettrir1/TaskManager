package com.techsales.taskmanager.notes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.FragmentNoteListBinding;
import com.techsales.taskmanager.notes.container.AddNotesActivity;

public class NoteListFragment extends BaseFragment implements NoteListContract.View {

    public static Fragment getInstance() {
        return new NoteListFragment();
    }

    FragmentNoteListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_list, null, false);

        binding.addNotes.setOnClickListener(view -> AddNotesActivity.start(getActivity()));
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void showProgress() {

    }
}
