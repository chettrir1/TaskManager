package com.techsales.taskmanager.notes.container;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.techsales.taskmanager.BaseActivity;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.ActivityAddNotesBinding;
import com.techsales.taskmanager.notes.AddNotesFragment;

public class AddNotesActivity extends BaseActivity {

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, AddNotesActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddNotesBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_notes);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.addNotesContainer, AddNotesFragment.getInstance())
                .commit();
    }


}
