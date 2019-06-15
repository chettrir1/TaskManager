package com.techsales.taskmanager.data.model.notes;

import com.techsales.taskmanager.entity.NotesEntity;
import com.techsales.taskmanager.utils.Commons;

public class Notes {

    private int id;
    private String title;
    private String description;
    private String createdAt;
    private String updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    public static NotesEntity mapToNotesEntity(Notes notes){
        NotesEntity notesEntity = new NotesEntity();
        notesEntity.noteTitle = notes.title;
        notesEntity.noteDescription = notes.description;
        notesEntity.createdAt = String.valueOf(Commons.getCurrentDateTime());
        notesEntity.updatedAt = String.valueOf(Commons.getCurrentDateTime());
        return notesEntity;
    }
}
