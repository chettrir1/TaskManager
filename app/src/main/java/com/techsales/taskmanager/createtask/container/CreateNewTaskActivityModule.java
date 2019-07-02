package com.techsales.taskmanager.createtask.container;

import android.app.Activity;

import com.techsales.taskmanager.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class CreateNewTaskActivityModule {

    @Provides
    @ActivityScope
    Activity provideActivity(CreateNewTaskActivity activity) {
        return activity;
    }
}
