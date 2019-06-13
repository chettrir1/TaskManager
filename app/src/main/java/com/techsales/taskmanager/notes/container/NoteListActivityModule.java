package com.techsales.taskmanager.notes.container;

import android.app.Activity;

import com.techsales.taskmanager.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class NoteListActivityModule {

    @Provides
    @ActivityScope
    Activity provideActivity(NoteListActivity activity) {
        return activity;
    }
}
