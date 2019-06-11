package com.techsales.taskmanager.di;

import com.techsales.taskmanager.auth.login.LoginActivity;
import com.techsales.taskmanager.auth.login.LoginActivityModule;
import com.techsales.taskmanager.dashboard.container.DashboardActivity;
import com.techsales.taskmanager.dashboard.container.DashboardActivityModule;
import com.techsales.taskmanager.di.scope.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity loginActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {DashboardActivityModule.class, FragmentBindingModule.class})
    abstract DashboardActivity dashboardActivity();



}
