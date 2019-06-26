package com.techsales.taskmanager.contacts.container;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.techsales.taskmanager.BaseActivity;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.contacts.ContactsFragment;
import com.techsales.taskmanager.dashboard.DashboardFragment;
import com.techsales.taskmanager.databinding.ActivityContactsBinding;

public class ContactsActivity extends BaseActivity {

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ContactsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ActivityContactsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_contacts);

        if (binding.toolbar.customToolbar != null) {
            setSupportActionBar(binding.toolbar.customToolbar);
            binding.toolbar.ivToolbarImage.setImageResource(R.drawable.ic_back);
            binding.toolbar.tvToolbarText.setText(R.string.text_employee_contacts);
            binding.toolbar.ivToolbarImage.setOnClickListener(view -> onBackPressed());
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contactsContainer, ContactsFragment.getInstance())
                .commit();
    }
}
