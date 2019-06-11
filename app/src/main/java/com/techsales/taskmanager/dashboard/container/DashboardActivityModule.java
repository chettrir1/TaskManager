package com.techsales.taskmanager.dashboard.container;

import android.app.Activity;

import com.techsales.taskmanager.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class DashboardActivityModule {

    @Provides
    @ActivityScope
    Activity provideActivity(DashboardActivity activity) {
        return activity;
    }
}
