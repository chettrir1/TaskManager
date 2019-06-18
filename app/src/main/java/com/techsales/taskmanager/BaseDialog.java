package com.techsales.taskmanager;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.techsales.taskmanager.data.Data;
import com.techsales.taskmanager.di.TaskManagerComponent;

import javax.inject.Inject;

public class BaseDialog extends BottomSheetDialogFragment {
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
