package com.techsales.taskmanager.data.model.viewmodel.notes;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.data.model.notes.Notes;

public class NotesViewModel extends BaseObservable {
    private Notes items;
    private Context context;

    public NotesViewModel(Context context, Notes items) {
        this.context = context;
        this.items = items;
    }

    @Bindable
    public int getId() {
        return items.getId();
    }

    @Bindable
    public String getNotesTitle() {
        return items.getTitle();
    }

    @Bindable
    public String getNotesDesc() {
        return items.getDescription();
    }
}
