package com.techsales.taskmanager.entity;


import com.techsales.taskmanager.Constants;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Constants.TBL_NOTES)
public class NotesEntity {
    @PrimaryKey
    @ColumnInfo(name = Constants.NOTE_ID)public long noteId;
    @ColumnInfo(name = Constants.NOTE_TITLE)public String noteTitle;
    @ColumnInfo(name = Constants.NOTE_DESCRIPTION)public String noteDescription;
    @ColumnInfo(name = Constants.NOTE_CREATED_AT) public String createdAt;
    @ColumnInfo(name = Constants.NOTE_UPDATED_AT) public String updatedAt;
}
