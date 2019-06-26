package com.techsales.taskmanager.contacts;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;

import io.reactivex.disposables.Disposable;

public class ContactsPresenter implements ContactsContract.Presenter {

    private final TaskManagerComponent component;
    private ContactsContract.View view;
    private Disposable disposable;

    ContactsPresenter(TaskManagerComponent component, ContactsContract.View view) {
        this.component = component;
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        Commons.dispose(disposable);
    }
}
