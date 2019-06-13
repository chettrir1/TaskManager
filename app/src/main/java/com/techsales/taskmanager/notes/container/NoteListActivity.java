package com.techsales.taskmanager.notes.container;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.databinding.ActivityNoteListBinding;
import com.techsales.taskmanager.notes.NoteListFragment;

public class NoteListActivity extends AppCompatActivity {

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, NoteListActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNoteListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_note_list);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.noteListContainer, NoteListFragment.getInstance())
                .commit();
    }
}
