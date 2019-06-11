package com.techsales.taskmanager.dashboard;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardTopRecyclerViewModel;
import com.techsales.taskmanager.databinding.ViewholderDashboardTopRecyclerItemBinding;
import com.techsales.taskmanager.views.LoadMoreAdapter;

import java.util.List;

class TopRecyclerAdapter extends LoadMoreAdapter<TopRecyclerAdapter.TaggedQuestionsHolder> {

    private final List<DashboardTopRecyclerViewModel> dashboardItems;
    private final RecyclerItemClickListener listener;

    public TopRecyclerAdapter(@NonNull RecyclerView recyclerView,
                              List<DashboardTopRecyclerViewModel> dashboardItems,
                              RecyclerItemClickListener listener) {
        super(recyclerView);
        this.dashboardItems = dashboardItems;
        this.listener = listener;
    }

    @Override
    public int getItemCount_() {
        return dashboardItems.size();
    }

    @Override
    public TaggedQuestionsHolder onCreateViewHolder_(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new TaggedQuestionsHolder(DataBindingUtil.inflate(inflater, R.layout.viewholder_dashboard_top_recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder_(TaggedQuestionsHolder holder, int position) {
        if (dashboardItems != null) {
            holder.binding.setDashboardTop(dashboardItems.get(position));
        }

        holder.binding.getRoot().setOnClickListener(v -> {
            if (dashboardItems != null)
                listener.onRecyclerItemClicked(dashboardItems.get(position), position);
        });
        holder.binding.executePendingBindings();
    }

    void addMoreItems(List<DashboardTopRecyclerViewModel> items, boolean hasMoreItems) {
        final int count = this.dashboardItems.size();
        this.dashboardItems.addAll(items);
        onItemsAdded(count, items.size(), hasMoreItems);
    }

    interface RecyclerItemClickListener {
        void onRecyclerItemClicked(DashboardTopRecyclerViewModel items, int position);
    }

    static class TaggedQuestionsHolder extends RecyclerView.ViewHolder {
        private final ViewholderDashboardTopRecyclerItemBinding binding;

        TaggedQuestionsHolder(ViewholderDashboardTopRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
