package com.techsales.taskmanager.createtask.container;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.techsales.taskmanager.BaseActivity;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.createtask.CreateNewTaskFragment;
import com.techsales.taskmanager.databinding.ActivityCreateNewTaskBinding;

public class CreateNewTaskActivity extends BaseActivity {

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, CreateNewTaskActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCreateNewTaskBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_create_new_task);
        if (binding.includeToolbar.customToolbar != null) {
            setSupportActionBar(binding.includeToolbar.customToolbar);
            binding.includeToolbar.ivToolbarImage.setImageResource(R.drawable.ic_back);
            binding.includeToolbar.tvToolbarText.setText(R.string.create_new_task_toolbar_title);

            binding.includeToolbar.ivToolbarImage.setOnClickListener(view -> onBackPressed());
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.createTaskContainer, CreateNewTaskFragment.getInstance())
                .commit();
    }
}
