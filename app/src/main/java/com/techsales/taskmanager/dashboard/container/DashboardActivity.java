package com.techsales.taskmanager.dashboard.container;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.databinding.DataBindingUtil;

import com.techsales.taskmanager.BaseActivity;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.dashboard.DashboardFragment;
import com.techsales.taskmanager.databinding.ActivityDashboardBinding;

public class DashboardActivity extends BaseActivity {
    ActivityDashboardBinding binding;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, DashboardActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, DashboardFragment.getInstance())
                .commit();
        binding.ivToolbarImage.setImageResource(R.mipmap.ic_launcher_round);
        binding.tvToolbarText.setText(R.string.app_name);
        setSupportActionBar(binding.customToolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return super.onOptionsItemSelected(item);
    }
}
