package com.techsales.taskmanager.status.container;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.techsales.taskmanager.BaseActivity;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.ActivityStatusBinding;
import com.techsales.taskmanager.status.StatusFragment;

public class StatusActivity extends BaseActivity {

    private String toolbarTitle = "Status";

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, StatusActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStatusBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_status);
        if (binding.includeToolbar.customToolbar != null) {
            setSupportActionBar(binding.includeToolbar.customToolbar);
            binding.includeToolbar.ivToolbarImage.setImageResource(R.drawable.ic_back);
            binding.includeToolbar.tvToolbarText.setText(toolbarTitle);
            binding.includeToolbar.ivToolbarImage.setOnClickListener(view -> onBackPressed());
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.statusContainer, StatusFragment.getInstance())
                .commit();
    }
}
