package com.techsales.taskmanager.status;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.status.StatusViewModel;
import com.techsales.taskmanager.databinding.ViewholderStatusRecyclerItemBinding;
import com.techsales.taskmanager.views.LoadMoreAdapter;

import java.util.List;

public class StatusRecyclerAdapter extends LoadMoreAdapter<StatusRecyclerAdapter.StatusViewHolder> {
    private final List<StatusViewModel> items;
    private final StatusItemClickListener listener;

    StatusRecyclerAdapter(@NonNull RecyclerView recyclerView,
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
        return new StatusViewHolder(DataBindingUtil.inflate(inflater, R.layout.viewholder_status_recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder_(StatusViewHolder holder, int position) {
        if (items != null) {
            holder.binding.setStatus(items.get(position));
        }
        holder.binding.getRoot().setOnClickListener(view -> {
            if (items != null)
                listener.onRecyclerItemClicked(items.get(position), position);
        });
        holder.binding.executePendingBindings();
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
        private final ViewholderStatusRecyclerItemBinding binding;

        StatusViewHolder(ViewholderStatusRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
