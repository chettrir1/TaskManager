package com.techsales.taskmanager.notes;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardBottomRecyclerViewModel;
import com.techsales.taskmanager.data.model.viewmodel.notes.NotesViewModel;
import com.techsales.taskmanager.databinding.ViewholderDashboardBottomRecyclerItemBinding;
import com.techsales.taskmanager.databinding.ViewholderNotesRecyclerItemBinding;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder> {

    private List<NotesViewModel> items;
    private LayoutInflater layoutInflater;
    private NoteListAdapter.NoteListItemClickListener listener;

    class NoteListViewHolder extends RecyclerView.ViewHolder {

        private final ViewholderNotesRecyclerItemBinding binding;

        NoteListViewHolder(final ViewholderNotesRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    NoteListAdapter(List<NotesViewModel> items, NoteListAdapter.NoteListItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteListAdapter.NoteListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ViewholderNotesRecyclerItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.viewholder_notes_recycler_item, parent, false);
        return new NoteListAdapter.NoteListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListAdapter.NoteListViewHolder holder, final int position) {
        holder.binding.setNotes(items.get(position));
        holder.binding.getRoot().setOnClickListener(view -> {
            if (items != null)
                listener.onNoteListItemClicked(items.get(position), position);
        });

        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public interface NoteListItemClickListener {
        void onNoteListItemClicked(NotesViewModel items, int position);
    }
}