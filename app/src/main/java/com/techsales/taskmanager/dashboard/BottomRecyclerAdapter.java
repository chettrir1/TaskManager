package com.techsales.taskmanager.dashboard;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardBottomRecyclerViewModel;
import com.techsales.taskmanager.databinding.ViewholderDashboardBottomRecyclerItemBinding;
import com.techsales.taskmanager.views.LoadMoreAdapter;

import java.util.List;

class BottomRecyclerAdapter extends LoadMoreAdapter<BottomRecyclerAdapter.BottomRecyclerHolder> {

    private final List<DashboardBottomRecyclerViewModel> items;
    private final BottomRecyclerItemClickListener listener;

    public BottomRecyclerAdapter(@NonNull RecyclerView recyclerView,
                                 List<DashboardBottomRecyclerViewModel> bottomRecyclerViewModels,
                                 BottomRecyclerItemClickListener listener) {
        super(recyclerView);
        this.items = bottomRecyclerViewModels;
        this.listener = listener;
    }

    @Override
    public int getItemCount_() {
        return items.size();
    }

    @Override
    public BottomRecyclerAdapter.BottomRecyclerHolder onCreateViewHolder_(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new BottomRecyclerHolder(DataBindingUtil.inflate(inflater, R.layout.viewholder_dashboard_bottom_recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder_(BottomRecyclerAdapter.BottomRecyclerHolder holder, int position) {
        if (items != null) {
            holder.binding.setDashboardBottom(items.get(position));
        }
        holder.binding.getRoot().setOnClickListener(view -> {
            if (items != null)
                listener.onBottomRecyclerItemClicked(items.get(position), position);
        });
        holder.binding.executePendingBindings();
    }

    void addMoreItems(List<DashboardBottomRecyclerViewModel> items, boolean hasMoreItems) {
        final int count = this.items.size();
        this.items.addAll(items);
        onItemsAdded(count, items.size(), hasMoreItems);
    }

    interface BottomRecyclerItemClickListener {
        void onBottomRecyclerItemClicked(DashboardBottomRecyclerViewModel items, int position);
    }

    static class BottomRecyclerHolder extends RecyclerView.ViewHolder {
        private final ViewholderDashboardBottomRecyclerItemBinding binding;

        BottomRecyclerHolder(ViewholderDashboardBottomRecyclerItemBinding bottomRecyclerItemBinding) {
            super(bottomRecyclerItemBinding.getRoot());
            this.binding = bottomRecyclerItemBinding;
        }
    }
}
