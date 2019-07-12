package com.techsales.taskmanager.dao;

import com.techsales.taskmanager.data.model.notes.Notes;
import com.techsales.taskmanager.entity.NotesEntity;

import java.util.List;
import java.util.Observer;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NotesEntity notesEntity);

    @Query("SELECT id," +
            " title," +
            " description," +
            " created_at AS createdAt," +
            " updated_at AS updatedAt FROM notes")
    Single<List<Notes>> getNotes();

}


