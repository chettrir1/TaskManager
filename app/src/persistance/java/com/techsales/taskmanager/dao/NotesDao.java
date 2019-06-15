package com.techsales.taskmanager.dao;

import com.techsales.taskmanager.data.model.notes.Notes;
import com.techsales.taskmanager.entity.NotesEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(NotesEntity notesEntity);

    @Query("SELECT id," +
            " title," +
            " description," +
            " created_at AS createdAt," +
            " updated_at AS updatedAt FROM notes")
    Single<List<Notes>> getNotes();

//    @Query("Select * from notes where user_id=:userId")
//    Notes getNotesByUserId();
}


