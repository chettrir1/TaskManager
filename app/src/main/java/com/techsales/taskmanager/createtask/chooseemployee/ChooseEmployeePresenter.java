package com.techsales.taskmanager.createtask.chooseemployee;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;

import io.reactivex.disposables.Disposable;

class ChooseEmployeePresenter implements ChooseEmployeeContract.Presenter {

    private final TaskManagerComponent component;
    private ChooseEmployeeContract.View view;
    private Disposable disposable;

    ChooseEmployeePresenter(TaskManagerComponent component, ChooseEmployeeContract.View view) {
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
