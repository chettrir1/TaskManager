package com.techsales.taskmanager.notes;

import com.techsales.taskmanager.di.TaskManagerComponent;

class NoteListPresenter implements NoteListContract.Presenter {
    private final TaskManagerComponent component;
    private final NoteListContract.View view;

    NoteListPresenter(TaskManagerComponent component, NoteListContract.View view) {
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
    public void getSavedNotes() {
        component.data().getAllNotes();
    }
}
