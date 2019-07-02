package com.techsales.taskmanager.createtask;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;

import io.reactivex.disposables.Disposable;

public class CreateNewTaskPresenter implements CreateNewTaskContract.Presenter {

    private final TaskManagerComponent component;
    private CreateNewTaskContract.View view;
    private Disposable disposable;

    CreateNewTaskPresenter(TaskManagerComponent component, CreateNewTaskContract.View view) {
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
