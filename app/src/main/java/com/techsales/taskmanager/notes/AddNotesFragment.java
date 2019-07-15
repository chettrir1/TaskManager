package com.techsales.taskmanager.notes;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.FragmentAddNotesBinding;
import com.techsales.taskmanager.utils.Commons;

import javax.inject.Inject;

public class AddNotesFragment extends BaseFragment implements AddNotesContract.View {
    private static final String NOTE_ID = "id";
    private static final String ADD_NOTE_MODE = "mode";
    private static final String TITLE_NAME = "title";
    private static final String TITLE_DESCRIPTION = "description";
    private static final String MODE_INSERT = "insert";
    private String mode;
    private int id;

    public static Fragment getInstance(String id, String mode, String title, String description) {
        AddNotesFragment fragment = new AddNotesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NOTE_ID, id);
        bundle.putString(ADD_NOTE_MODE, mode);
        bundle.putString(TITLE_NAME, title);
        bundle.putString(TITLE_DESCRIPTION, description);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Inject
    AddNotesContract.Presenter presenter;

    private FragmentAddNotesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_notes, null, false);
        getDataFromBundle();

        binding.includeToolbar.ivSave.setOnClickListener(view ->
        {
            if (mode.equals(MODE_INSERT)) {
                presenter.insertNotes(binding.etTitle.getText().toString(),
                        binding.etDescription.getText().toString());
            } else {
                presenter.updateNote(id, binding.etTitle.getText().toString(),
                        binding.etDescription.getText().toString());
            }
        });

        binding.includeToolbar.ivBack.setOnClickListener(view -> onBackPressed());

        return binding.getRoot();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showEmptyFields(String message) {
        Commons.showSnackBar(component.context(), binding.coordinatorLayout, message);
    }

    @Override
    public void showNoteAddSuccess() {
        onBackPressed();
    }

    @Override
    public void showNoteAddedError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void getDataFromBundle() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mode = bundle.getString(ADD_NOTE_MODE);
            if (mode != null)
                if (!mode.equals(MODE_INSERT)) {
                    id = Integer.parseInt(bundle.getString(NOTE_ID));
                }
            String title = bundle.getString(TITLE_NAME);
            String description = bundle.getString(TITLE_DESCRIPTION);
            if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description)) {
                binding.etTitle.setText(title);
                binding.etDescription.setText(description);
            }
        }
    }

    private void onBackPressed() {
        if (getActivity() != null)
            getActivity().onBackPressed();
    }
}
