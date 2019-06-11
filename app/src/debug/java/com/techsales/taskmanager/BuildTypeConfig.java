package com.techsales.taskmanager;

import android.content.Context;

import com.facebook.stetho.Stetho;

import javax.inject.Inject;

public class BuildTypeConfig {

    private final Context context;

    @Inject
    public BuildTypeConfig(Context context) {
        this.context = context;
    }

    void set() {
        Stetho.initializeWithDefaults(context);
    }
}
