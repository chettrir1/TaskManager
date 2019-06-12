package com.techsales.taskmanager.dashboard;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardTopRecyclerViewModel;
import com.techsales.taskmanager.databinding.ViewholderDashboardTopRecyclerItemBinding;

import java.util.List;

public class TopRecyclerAdapter extends RecyclerView.Adapter<TopRecyclerAdapter.TopRecyclerViewHolder> {

    private List<DashboardTopRecyclerViewModel> items;
    private LayoutInflater layoutInflater;
    private TopRecyclerItemClickListener listener;

    class TopRecyclerViewHolder extends RecyclerView.ViewHolder {

        private final ViewholderDashboardTopRecyclerItemBinding binding;

        TopRecyclerViewHolder(final ViewholderDashboardTopRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    TopRecyclerAdapter(List<DashboardTopRecyclerViewModel> items, TopRecyclerItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TopRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ViewholderDashboardTopRecyclerItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.viewholder_dashboard_top_recycler_item, parent, false);
        return new TopRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TopRecyclerViewHolder holder, final int position) {

        if (items != null) {
            holder.binding.setDashboardTop(items.get(position));
        }
        holder.binding.getRoot().setOnClickListener(view -> {
            if (items != null)
                listener.onTopRecyclerItemClicked(items.get(position), position);
        });
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface TopRecyclerItemClickListener {
        void onTopRecyclerItemClicked(DashboardTopRecyclerViewModel items, int position);
    }
}
