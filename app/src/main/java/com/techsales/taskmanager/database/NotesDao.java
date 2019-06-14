package com.techsales.taskmanager.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.techsales.taskmanager.data.model.notes.Notes;

import java.util.List;

@Dao
public interface NotesDao {
    @Insert
    Long insertTask(Notes note);

    @Query("SELECT * FROM Notes ORDER BY created_at desc")
    LiveData<List<Notes>> fetchAllTasks();

    @Query("SELECT * FROM Notes WHERE id =:taskId")
    LiveData<Notes> getTask(int taskId);

    @Update
    void updateTask(Notes note);

    @Delete
    void deleteTask(Notes note);
}
