package com.techsales.taskmanager.dashboard;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class DashboardFragmentModule {

    @FragmentScope
    @Provides
    DashboardContract.View provideFragment(DashboardFragment fragment) {
        return fragment;
    }

    @FragmentScope
    @Provides
    DashboardContract.Presenter providePresenter(TaskManagerComponent component, DashboardContract.View view) {
        return new DashboardPresenter(component, view);
    }

}
