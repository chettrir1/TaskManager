package com.techsales.taskmanager;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.techsales.taskmanager.data.Data;
import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Permission;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class BaseFragment extends DaggerFragment {
    protected TaskManagerComponent component;

    @Inject
    protected Data data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this.getActivity();
        if (activity != null) {
            this.component = TaskManagerApp.component(activity);
        }
    }

}
