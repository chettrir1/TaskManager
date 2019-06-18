package com.techsales.taskmanager.dashboard.viewtask;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewTaskFragmentModule {

    @FragmentScope
    @Provides
    ViewTaskContract.View provideFragment(ViewTaskFragment fragment) {
        return fragment;
    }

    @Provides
    @FragmentScope
    ViewTaskContract.Presenter providePresenter(TaskManagerComponent component, ViewTaskContract.View view) {
        return new ViewTaskPresenter(component, view);
    }
}
