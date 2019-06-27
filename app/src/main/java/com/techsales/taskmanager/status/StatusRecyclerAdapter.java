package com.techsales.taskmanager.status;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techsales.taskmanager.data.model.viewmodel.status.StatusViewModel;
import com.techsales.taskmanager.views.LoadMoreAdapter;

import java.util.List;

public class StatusRecyclerAdapter extends LoadMoreAdapter<StatusRecyclerAdapter.StatusViewHolder> {
    private final List<StatusViewModel> items;
    private final StatusItemClickListener listener;

    public StatusRecyclerAdapter(@NonNull RecyclerView recyclerView,
                                 List<StatusViewModel> items,
                                 StatusItemClickListener listener) {
        super(recyclerView);
        this.items = items;
        this.listener = listener;
    }

    @Override
    public int getItemCount_() {
        return items.size();
    }

    @Override
    public StatusViewHolder onCreateViewHolder_(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder_(StatusViewHolder holder, int position) {

    }

    void addMoreItems(List<StatusViewModel> items, boolean hasMoreItems) {
        final int count = this.items.size();
        this.items.addAll(items);
        onItemsAdded(count, items.size(), hasMoreItems);
    }

    interface StatusItemClickListener {
        void onRecyclerItemClicked(StatusViewModel items, int position);
    }

    class StatusViewHolder extends RecyclerView.ViewHolder {

        public StatusViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
