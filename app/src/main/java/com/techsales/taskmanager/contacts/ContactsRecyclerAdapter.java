package com.techsales.taskmanager.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.contacts.ContactsviewModel;
import com.techsales.taskmanager.databinding.ViewholderContactsRecyclerItemBinding;

import java.util.List;

public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ContactsViewHolder> {
    private List<ContactsviewModel> items;
    private LayoutInflater layoutInflater;
    private ContactsItemClickListener listener;

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        ViewholderContactsRecyclerItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.viewholder_contacts_recycler_item, parent, false);
        return new ContactsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        holder.binding.setContacts(items.get(position));
        holder.binding.getRoot().setOnClickListener(view -> {
            if (items != null)
                listener.onContactsItemClicked(items.get(position), position);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderContactsRecyclerItemBinding binding;

        ContactsViewHolder(final ViewholderContactsRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    ContactsRecyclerAdapter(List<ContactsviewModel> items, ContactsItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public interface ContactsItemClickListener {
        void onContactsItemClicked(ContactsviewModel items, int position);
    }
}
