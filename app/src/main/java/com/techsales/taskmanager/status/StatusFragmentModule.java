package com.techsales.taskmanager.status;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class StatusFragmentModule {

    @FragmentScope
    @Provides
    StatusContract.View provideFragment(StatusFragment fragment) {
        return fragment;
    }

    @FragmentScope
    @Provides
    StatusContract.Presenter providePresenter(TaskManagerComponent component, StatusContract.View view) {
        return new StatusPresenter(component, view);
    }
}
