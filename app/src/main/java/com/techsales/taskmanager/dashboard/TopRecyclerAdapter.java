package com.techsales.taskmanager.dashboard;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.TopItems;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardTopRecyclerViewModel;
import com.techsales.taskmanager.databinding.ViewholderDashboardTopRecyclerItemBinding;

import java.util.List;

public class TopRecyclerAdapter extends RecyclerView.Adapter<TopRecyclerAdapter.TopRecyclerViewHolder> {

    private List<DashboardTopRecyclerViewModel> topItem;
    private LayoutInflater layoutInflater;
    private RecyclerItemClickListener listener;

    public class TopRecyclerViewHolder extends RecyclerView.ViewHolder {

        private final ViewholderDashboardTopRecyclerItemBinding binding;

        public TopRecyclerViewHolder(final ViewholderDashboardTopRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    public TopRecyclerAdapter(List<DashboardTopRecyclerViewModel> topItem, RecyclerItemClickListener listener) {
        this.topItem = topItem;
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
        holder.binding.setDashboardTop(topItem.get(position));
    }

    @Override
    public int getItemCount() {
        return topItem.size();
    }

    public interface RecyclerItemClickListener {
        void onRecyclerItemClicked(TopItems topItems);
    }
}
