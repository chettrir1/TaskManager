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
    private static final String TITLE_NAME = "title";
    private static final String TITLE_DESCRIPTION = "description";

    public static void start(Activity activity, String title, String description) {
        Intent intent = new Intent(activity, AddNotesActivity.class);
        intent.putExtra(TITLE_NAME, title);
        intent.putExtra(TITLE_DESCRIPTION, description);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddNotesBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_notes);
        String title = getIntent().getStringExtra(TITLE_NAME);
        String description = getIntent().getStringExtra(TITLE_DESCRIPTION);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.addNotesContainer, AddNotesFragment.getInstance(title, description))
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
