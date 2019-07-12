package com.techsales.taskmanager.data.local.database;

import com.techsales.taskmanager.DatabaseManager;
import com.techsales.taskmanager.data.model.notes.Notes;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class DatabaseRepo {

    @Inject
    DatabaseManager databaseManager;

    @Inject
    DatabaseRepo() {
    }

    public Completable insertToNotes(Notes notes) {
        return Completable.fromAction(() -> databaseManager.getNotesDao()
                .insert(Notes.mapToNotesEntity(notes)));
    }

    public Single<List<Notes>> getAllNotes() {
        return databaseManager.getNotesDao().getNotes();

    }

}
