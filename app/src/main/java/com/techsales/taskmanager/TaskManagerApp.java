package com.techsales.taskmanager;

import android.content.Context;

import com.techsales.taskmanager.di.DaggerTaskManagerComponent;
import com.techsales.taskmanager.di.TaskManagerComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class TaskManagerApp extends DaggerApplication {

    private TaskManagerComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static TaskManagerComponent component(Context context) {

        return ((TaskManagerApp) context.getApplicationContext()).component;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        this.component = DaggerTaskManagerComponent.builder().application(this).build();
        this.component.buildType().buildTypeConfig().set();
        return component;
    }
}
