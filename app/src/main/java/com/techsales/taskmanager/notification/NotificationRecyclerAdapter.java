package com.techsales.taskmanager.notification;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.notification.NotificationViewModel;
import com.techsales.taskmanager.databinding.ViewholderNotificationRecyclerItemBinding;

import java.util.List;

public class NotificationRecyclerAdapter extends RecyclerView.Adapter<NotificationRecyclerAdapter.NotificationRecyclerViewHolder> {
    private List<NotificationViewModel> items;
    private LayoutInflater layoutInflater;

    private NotificationItemClickListener listener;

    @NonNull
    @Override
    public NotificationRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        ViewholderNotificationRecyclerItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.viewholder_notification_recycler_item, parent, false);
        return new NotificationRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationRecyclerViewHolder holder, int position) {
        holder.binding.setNotification(items.get(position));
        holder.binding.getRoot().setOnClickListener(view -> {
            if (items != null)
                listener.onNotificationItemClicked(items.get(position), position);

        });
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class NotificationRecyclerViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderNotificationRecyclerItemBinding binding;

        NotificationRecyclerViewHolder(final ViewholderNotificationRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    NotificationRecyclerAdapter(List<NotificationViewModel> items, NotificationItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public interface NotificationItemClickListener {
        void onNotificationItemClicked(NotificationViewModel items, int position);
    }

}
