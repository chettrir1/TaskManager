package com.techsales.taskmanager.entity;


import com.techsales.taskmanager.Constants;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Constants.TBL_NOTES)
public class NotesEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")public int id;
    @ColumnInfo(name = "title")public String noteTitle;
    @ColumnInfo(name = "description")public String noteDescription;
    @ColumnInfo(name = "created_at") public String createdAt;
    @ColumnInfo(name = "updated_at") public String updatedAt;
}
