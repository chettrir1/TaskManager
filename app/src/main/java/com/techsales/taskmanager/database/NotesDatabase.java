package com.techsales.taskmanager.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.techsales.taskmanager.data.model.notes.Notes;

@Database(entities = {Notes.class}, version = 1, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NotesDao notesDao();
}
