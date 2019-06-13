package com.techsales.taskmanager.profile.container;

import android.app.Activity;

import com.techsales.taskmanager.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileActivityModule {
    @Provides
    @ActivityScope
    Activity provideActivity(ProfileActivity activity) {
        return activity;
    }
}
