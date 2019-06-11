package com.techsales.taskmanager.di;

import android.content.Context;

import com.techsales.taskmanager.TaskManagerApp;

import dagger.Binds;
import dagger.Module;

@Module
abstract class ApplicationModule {

    @Binds
    abstract Context bindsContext(TaskManagerApp app);
}
