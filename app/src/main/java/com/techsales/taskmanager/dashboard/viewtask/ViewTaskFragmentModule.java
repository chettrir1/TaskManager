package com.techsales.taskmanager.dashboard.viewtask;

import com.techsales.taskmanager.dashboard.DashboardContract;
import com.techsales.taskmanager.dashboard.DashboardFragment;
import com.techsales.taskmanager.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewTaskFragmentModule {

    @FragmentScope
    @Provides
    DashboardContract.View provideFragment(DashboardFragment fragment) {
        return fragment;
    }
}
