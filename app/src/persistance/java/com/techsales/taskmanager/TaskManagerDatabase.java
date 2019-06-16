package com.techsales.taskmanager;


import android.content.Context;

import com.techsales.taskmanager.dao.NotesDao;
import com.techsales.taskmanager.entity.NotesEntity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//add other entities here
@Database(entities = {
        NotesEntity.class
}, version = 1, exportSchema = false)
abstract class TaskManagerDatabase extends RoomDatabase {

    private static TaskManagerDatabase instance;
    public static TaskManagerDatabase getInstance(Context context) {
        if (instance == null) {
            instance = createInstance(context);
        }
        return instance;
    }

    private static TaskManagerDatabase createInstance(Context context) {
        return Room
                .databaseBuilder(context, TaskManagerDatabase.class, Constants.DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    //Add other Dao here
    abstract NotesDao getNotesDao();

}
