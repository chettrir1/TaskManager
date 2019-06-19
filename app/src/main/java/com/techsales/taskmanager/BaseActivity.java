package com.techsales.taskmanager;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.techsales.taskmanager.data.Data;
import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Permission;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    protected TaskManagerComponent component;

    @Inject
    protected Data data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.component = TaskManagerApp.component(this);
    }
}
