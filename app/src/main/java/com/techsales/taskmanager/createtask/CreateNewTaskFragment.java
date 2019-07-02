package com.techsales.taskmanager.createtask;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;

public class CreateNewTaskFragment extends BaseFragment implements CreateNewTaskContract.View {


    public static Fragment getInstance() {
        return new CreateNewTaskFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_new_task, container, false);
    }

}
