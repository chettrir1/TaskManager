package com.techsales.taskmanager.status.container;

import android.app.Activity;

import com.techsales.taskmanager.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class StatusActivityModule {

    @Provides
    @ActivityScope
    Activity provideActivity(StatusActivity activity) {
        return activity;
    }
}
