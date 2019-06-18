package com.techsales.taskmanager;

import android.os.Bundle;

import com.techsales.taskmanager.data.Data;
import com.techsales.taskmanager.di.TaskManagerComponent;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import dagger.android.support.DaggerAppCompatDialogFragment;

public class BaseDialogFragment extends DaggerAppCompatDialogFragment {
    protected TaskManagerComponent component;

    @Inject
    protected Data data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            this.component = TaskManagerApp.component(requireContext());
    }
}
