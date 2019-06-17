package com.techsales.taskmanager.notes;

import android.text.TextUtils;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.notes.Notes;
import com.techsales.taskmanager.di.TaskManagerComponent;

class AddNotesPresenter implements AddNotesContract.Presenter {

    private final TaskManagerComponent component;
    private AddNotesContract.View view;

    AddNotesPresenter(TaskManagerComponent component, AddNotesContract.View view) {
        this.component = component;
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void insertNotes(String title, String description) {
        view.showProgress();
        if (TextUtils.isEmpty(title)) {
            view.showEmptyFields(component.context().getResources().getString(R.string.empty_field_message));
        } else if (TextUtils.isEmpty(description)) {
            view.showEmptyFields(component.context().getResources().getString(R.string.empty_field_message));
        } else {
            Notes notes = new Notes();
            notes.setTitle(title);
            notes.setDescription(description);
            component.data().insertNotes(notes);
            view.showNoteAddSuccess();
        }
    }
}
