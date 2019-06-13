package com.techsales.taskmanager.notes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.FragmentNoteListBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteListFragment extends Fragment implements NoteListContract.View {

    private FragmentNoteListBinding binding;

    public static Fragment getInstance() {
        NoteListFragment fragment = new NoteListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_list, null, false);

        return binding.getRoot();
    }

}
