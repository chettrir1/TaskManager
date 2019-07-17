package com.techsales.taskmanager.notes.container;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.techsales.taskmanager.BaseActivity;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.ActivityNoteListBinding;
import com.techsales.taskmanager.notes.NoteListFragment;

public class NoteListActivity extends BaseActivity {

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, NoteListActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNoteListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_note_list);

        setSupportActionBar(binding.includeToolbar.customToolbar);
        binding.includeToolbar.ivToolbarImage.setImageResource(R.drawable.ic_back);
        binding.includeToolbar.ivToolbarImage.setOnClickListener(view -> onBackPressed());
        binding.includeToolbar.tvToolbarText.setText(getResources().getString(R.string.notes_toolbar_title));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.noteListContainer, NoteListFragment.getInstance())
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
