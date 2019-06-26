package com.techsales.taskmanager.notification.container;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.techsales.taskmanager.BaseActivity;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.dashboard.container.DashboardActivity;
import com.techsales.taskmanager.databinding.ActivityNotificationBinding;
import com.techsales.taskmanager.notification.NotificationFragment;

public class NotificationActivity extends BaseActivity {

    private ActivityNotificationBinding binding;

    public static void start(DashboardActivity activity) {
        Intent intent = new Intent(activity, NotificationActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification);
        if (binding.includeToolbar.customToolbar != null) {
            setSupportActionBar(binding.includeToolbar.customToolbar);
            binding.includeToolbar.ivToolbarImage.setImageResource(R.drawable.ic_back);
            binding.includeToolbar.tvToolbarText.setText(R.string.text_notification);
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.notificationContainer, NotificationFragment.getInstance())
                .commit();
    }
}
