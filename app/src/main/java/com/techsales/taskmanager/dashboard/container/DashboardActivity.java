package com.techsales.taskmanager.dashboard.container;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.techsales.taskmanager.BaseActivity;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.auth.login.LoginActivity;
import com.techsales.taskmanager.contacts.container.ContactsActivity;
import com.techsales.taskmanager.dashboard.DashboardFragment;
import com.techsales.taskmanager.databinding.ActivityDashboardBinding;
import com.techsales.taskmanager.notes.container.NoteListActivity;
import com.techsales.taskmanager.profile.container.ProfileActivity;

public class DashboardActivity extends BaseActivity {
    private ActivityDashboardBinding binding;

    public static void start(Activity activity, String userId) {
        Intent intent = new Intent(activity, DashboardActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        setSupportActionBar(binding.customToolbar);
        binding.ivToolbarImage.setImageResource(R.mipmap.ic_launcher_round);
        binding.tvToolbarText.setText(R.string.app_name);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.dashboardContainer, DashboardFragment.getInstance())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile_src:
                ProfileActivity.start(this);
                break;

            case R.id.phone_src:
                ContactsActivity.start(this);
                break;

            case R.id.note_src:
                NoteListActivity.start(this);
                break;

            case R.id.logout_src:
                data.logout();
                onLogoutSelection();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onLogoutSelection() {
        Activity activity = this;
        startActivity(new Intent(activity, LoginActivity.class));
        activity.finish();
    }


}
