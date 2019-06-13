package com.techsales.taskmanager.notes.container;

import android.app.Activity;

import com.techsales.taskmanager.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AddNotesActivityModule {

    @Provides
    @ActivityScope
    Activity provideActivity(AddNotesActivity activity) {
        return activity;
    }
}
