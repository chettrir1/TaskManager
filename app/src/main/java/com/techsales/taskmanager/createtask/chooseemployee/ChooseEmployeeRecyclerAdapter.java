package com.techsales.taskmanager.createtask.chooseemployee;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.chooseemployee.ChooseEmployeeViewModel;
import com.techsales.taskmanager.databinding.ViewholderChooseEmployeeRecyclerItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ChooseEmployeeRecyclerAdapter extends RecyclerView.Adapter<ChooseEmployeeRecyclerAdapter.ChooseEmployeeViewHolder> {
    private List<ChooseEmployeeViewModel> items;
    private LayoutInflater layoutInflater;
    private ChooseEmployeeItemsClickListener listener;
    private List<String> employeeList;


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
        if (items.get(position).getChecked()) {
            holder.binding.cbAssignTo.setChecked(false);
        } else {
            holder.binding.cbAssignTo.setChecked(true);
        }
        holder.binding.setChooseEmployee(items.get(position));

        employeeList = new ArrayList<>();

        holder.binding.getRoot().setOnClickListener(view -> {
            if (items != null) {
                int adapterPosition = holder.getAdapterPosition();
                if (items.get(adapterPosition).getChecked()) {
                    holder.binding.cbAssignTo.setChecked(true);
                    items.get(adapterPosition).setChecked(true);
                    employeeList.add(items.get(adapterPosition).getEmployeeName());
                } else {
                    holder.binding.cbAssignTo.setChecked(false);
                    items.get(adapterPosition).setChecked(false);
                    employeeList.remove(items.get(adapterPosition).getEmployeeName());
                }
                Log.v("employeeList", employeeList + "");
/*
                listener.onChooseEmployeeItemClick(items.get(position), position, isChecked);
*/
            }
        });
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
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
        void onChooseEmployeeItemClick(ChooseEmployeeViewModel items, int position, boolean isChecked);
    }

}
