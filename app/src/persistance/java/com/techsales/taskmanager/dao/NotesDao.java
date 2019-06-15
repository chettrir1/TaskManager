package com.techsales.taskmanager.dao;

import com.techsales.taskmanager.entity.NotesEntity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(NotesEntity notesEntity);

}
