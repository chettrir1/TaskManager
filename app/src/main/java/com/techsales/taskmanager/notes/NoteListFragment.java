package com.techsales.taskmanager.notes;


import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.notes.NotesViewModel;
import com.techsales.taskmanager.databinding.FragmentNoteListBinding;
import com.techsales.taskmanager.notes.container.AddNotesActivity;
import com.techsales.taskmanager.utils.GridSpacingItemDecoration;

import java.util.List;

import javax.inject.Inject;

public class NoteListFragment extends BaseFragment implements NoteListContract.View {

    private FragmentNoteListBinding binding;

    private static final int SPACING_VALUE = 10;

    @Inject
    NoteListContract.Presenter presenter;

    public static Fragment getInstance() {
        return new NoteListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_list, null, false);
        binding.contentState.setContent(binding.content);
        initRecyclerView();
        initFabView();
        binding.addNotes.setOnClickListener(view -> {
            if (getActivity() != null) {
                AddNotesActivity.start(getActivity(), "", "");
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getSavedNotes();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void showProgress() {
        binding.contentState.showProgress(getResources().getString(R.string.contacts_loading_message));
    }

    @Override
    public void showDataLoadingSuccess(List<NotesViewModel> viewModel) {
        NoteListAdapter adapter = new NoteListAdapter(viewModel, presenter);
        binding.noteList.setAdapter(adapter);
        binding.contentState.showContent();
    }

    @Override
    public void showDataFetchError(String message) {
        binding.contentState.showError(R.drawable.ic_loading_error, message);
    }

    @Override
    public void onNotesItemClick(NotesViewModel items, int position) {
        AddNotesActivity.start(getActivity(), items.getNotesTitle(), items.getNotesDesc());
    }

    private void initRecyclerView() {
        binding.noteList.setLayoutManager(new GridLayoutManager(component.context(), 2));
        binding.noteList.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(), true));
        binding.noteList.setItemAnimator(new DefaultItemAnimator());
        binding.noteList.setNestedScrollingEnabled(false);
    }

    private int dpToPx() {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, SPACING_VALUE, r.getDisplayMetrics()));
    }

    private void initFabView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.noteList.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    if (dy > 0) {
                        // Scroll Down
                        if (binding.addNotes.isShown()) {
                            binding.addNotes.hide();
                        }
                    } else if (dy < 0) {
                        // Scroll Up
                        if (!binding.addNotes.isShown()) {
                            binding.addNotes.show();
                        }
                    }
                }
            });
        }
    }
}
