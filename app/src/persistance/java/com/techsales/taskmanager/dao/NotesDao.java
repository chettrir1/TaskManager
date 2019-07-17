package com.techsales.taskmanager.dao;

import com.techsales.taskmanager.data.model.notes.Notes;
import com.techsales.taskmanager.entity.NotesEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insert(NotesEntity notesEntity);

    @Query("SELECT id," +
            " title," +
            " description," +
            " created_at AS createdAt," +
            " updated_at AS updatedAt FROM notes")
    Single<List<Notes>> getNotes();

    @Query("UPDATE notes SET title=:title, description =:description WHERE id =:id")
    Completable updateNotes(int id, String title, String description);

    @Query("DELETE FROM notes")
    Completable delteAllNotes();
}


