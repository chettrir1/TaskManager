package com.techsales.taskmanager.profile.container;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.techsales.taskmanager.BaseActivity;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.dashboard.container.DashboardActivity;
import com.techsales.taskmanager.databinding.ActivityProfileBinding;
import com.techsales.taskmanager.profile.ProfileFragment;

public class ProfileActivity extends BaseActivity {

    private ActivityProfileBinding binding;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ProfileActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        setSupportActionBar(binding.customToolbar);

        binding.ivToolbarImage.setImageResource(R.drawable.ic_back);
        binding.ivToolbarImage.setOnClickListener(view -> onBackPressed());
        binding.tvToolbarText.setText(R.string.profile_toolbar_title);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.profileContainer, ProfileFragment.getInstance())
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
