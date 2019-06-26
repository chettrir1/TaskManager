package com.techsales.taskmanager.notification.container;

import android.app.Activity;

import com.techsales.taskmanager.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class NotificationActivityModule {

    @Provides
    @ActivityScope
    Activity provideActivity(NotificationActivity activity) {
        return activity;
    }
}
