package com.techsales.taskmanager.di;

import com.techsales.taskmanager.dashboard.DashboardFragment;
import com.techsales.taskmanager.dashboard.DashboardFragmentModule;
import com.techsales.taskmanager.di.scope.FragmentScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBindingModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = DashboardFragmentModule.class)
    abstract DashboardFragment dashboardFragment();
}
