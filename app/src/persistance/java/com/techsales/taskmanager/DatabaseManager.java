package com.techsales.taskmanager;

import android.content.Context;

import com.techsales.taskmanager.dao.NotesDao;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DatabaseManager {
    private final Context context;

    @Inject
    public DatabaseManager(Context context){
        this.context = context;
    }

    private TaskManagerDatabase getTaskManagerDatabase(){
        return TaskManagerDatabase.getInstance(context);
    }

    public NotesDao getNotesDao(){
        return getTaskManagerDatabase().getNotesDao();
    }

}
