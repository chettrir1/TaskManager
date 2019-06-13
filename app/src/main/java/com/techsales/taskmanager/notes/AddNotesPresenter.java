package com.techsales.taskmanager.notes;

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
}
