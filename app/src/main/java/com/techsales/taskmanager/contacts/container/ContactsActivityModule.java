package com.techsales.taskmanager.contacts.container;

import android.app.Activity;

import com.techsales.taskmanager.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactsActivityModule {

    @Provides
    @ActivityScope
    Activity provideActivity(ContactsActivity activity) {
        return activity;
    }
}
