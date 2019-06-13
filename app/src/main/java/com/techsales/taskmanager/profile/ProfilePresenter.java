package com.techsales.taskmanager.profile;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;

import io.reactivex.disposables.Disposable;

public class ProfilePresenter implements ProfileContract.Presenter {
    private final TaskManagerComponent component;
    private ProfileContract.View view;
    private Disposable disposable;

    ProfilePresenter(TaskManagerComponent component, ProfileContract.View view) {
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
