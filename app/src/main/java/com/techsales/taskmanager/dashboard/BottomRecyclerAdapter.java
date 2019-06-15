package com.techsales.taskmanager.dashboard;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardBottomRecyclerViewModel;
import com.techsales.taskmanager.databinding.ViewholderDashboardBottomRecyclerItemBinding;

import java.util.List;

public class BottomRecyclerAdapter extends RecyclerView.Adapter<BottomRecyclerAdapter.BottomRecyclerViewHolder> {

    private List<DashboardBottomRecyclerViewModel> items;
    private LayoutInflater layoutInflater;
    private BottomRecyclerItemClickListener listener;

    class BottomRecyclerViewHolder extends RecyclerView.ViewHolder {

        private final ViewholderDashboardBottomRecyclerItemBinding binding;

        BottomRecyclerViewHolder(final ViewholderDashboardBottomRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    BottomRecyclerAdapter(List<DashboardBottomRecyclerViewModel> items, BottomRecyclerItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BottomRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ViewholderDashboardBottomRecyclerItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.viewholder_dashboard_bottom_recycler_item, parent, false);
        return new BottomRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomRecyclerViewHolder holder, final int position) {
        holder.binding.setDashboardBottom(items.get(position));
        holder.binding.getRoot().setOnClickListener(view -> {
            if (items != null)
                listener.onBottomRecyclerItemClicked(items.get(position), position);
        });
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public interface BottomRecyclerItemClickListener {
        void onBottomRecyclerItemClicked(DashboardBottomRecyclerViewModel items, int position);
    }
}