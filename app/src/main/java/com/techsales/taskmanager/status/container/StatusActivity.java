package com.techsales.taskmanager.status.container;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.techsales.taskmanager.BaseActivity;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.ActivityStatusBinding;
import com.techsales.taskmanager.status.StatusFragment;
import com.techsales.taskmanager.utils.Constants;

public class StatusActivity extends BaseActivity {
    private static final String STATUS = "status";

    public static void start(Activity activity, String status) {
        Intent intent = new Intent(activity, StatusActivity.class);
        intent.putExtra(STATUS, status);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStatusBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_status);
        String status = getIntent().getStringExtra(STATUS);
        if (binding.includeToolbar.customToolbar != null) {
            setSupportActionBar(binding.includeToolbar.customToolbar);
            binding.includeToolbar.ivToolbarImage.setImageResource(R.drawable.ic_back);
            binding.includeToolbar.tvToolbarText.setText(getToolbarTitle(Integer.parseInt(status)));
            binding.includeToolbar.ivToolbarImage.setOnClickListener(view -> onBackPressed());
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.statusContainer, StatusFragment.getInstance(status))
                .commit();
    }

    private String getToolbarTitle(int status) {
        if (status == Constants.COUNT_ZERO) {
            return Constants.TITLE_NEW_TASK;
        }

        if (status == Constants.COUNT_ONE) {
            return Constants.TITLE_OPENED_TASK;
        }

        if (status == Constants.COUNT_TWO) {
            return Constants.TITLE_PENDING_TASK;
        }

        if (status == Constants.COUNT_THREE) {
            return Constants.TITLE_COMPLETED_TASK;
        }
        return "";
    }

}
