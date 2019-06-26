package com.techsales.taskmanager.notification.container;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.techsales.taskmanager.BaseActivity;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.notification.NotificationFragment;

public class NotificationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.notificationContainer, NotificationFragment.getInstance())
                .commit();
    }
}
