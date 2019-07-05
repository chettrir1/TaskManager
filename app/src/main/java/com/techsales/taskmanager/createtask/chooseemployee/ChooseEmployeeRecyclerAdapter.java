package com.techsales.taskmanager.createtask.chooseemployee;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.chooseemployee.ChooseEmployeeViewModel;
import com.techsales.taskmanager.databinding.ViewholderChooseEmployeeRecyclerItemBinding;

import java.util.List;

public class ChooseEmployeeRecyclerAdapter extends RecyclerView.Adapter<ChooseEmployeeRecyclerAdapter.ChooseEmployeeViewHolder> {
    private List<ChooseEmployeeViewModel> items;
    private LayoutInflater layoutInflater;
    private ChooseEmployeeItemsClickListener listener;
    private boolean isChecked;


    @NonNull
    @Override
    public ChooseEmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        ViewholderChooseEmployeeRecyclerItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.viewholder_choose_employee_recycler_item, parent, false);
        return new ChooseEmployeeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseEmployeeViewHolder holder, int position) {
        items.get(position).setChecked(false);
        holder.binding.setChooseEmployee(items.get(position));

        holder.binding.cbAssignTo.setOnCheckedChangeListener((compoundButton, checked) -> {
            isChecked = !checked;
            listener.onChooseEmployeeItemClick(items.get(position), isChecked);
        });
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ChooseEmployeeViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderChooseEmployeeRecyclerItemBinding binding;

        ChooseEmployeeViewHolder(final ViewholderChooseEmployeeRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    ChooseEmployeeRecyclerAdapter(List<ChooseEmployeeViewModel> items, ChooseEmployeeItemsClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public interface ChooseEmployeeItemsClickListener {
        void onChooseEmployeeItemClick(ChooseEmployeeViewModel items, boolean isChecked);
    }

}
