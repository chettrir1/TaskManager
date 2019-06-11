package com.techsales.taskmanager.di;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CommonsModule {

    @Singleton
    @Provides
    Gson providesGson() {
        return new Gson();
    }
}
