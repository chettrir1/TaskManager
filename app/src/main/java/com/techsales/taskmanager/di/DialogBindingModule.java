package com.techsales.taskmanager.di;

import com.techsales.taskmanager.dashboard.viewtask.changestatus.ChangeStatusFragment;
import com.techsales.taskmanager.dashboard.viewtask.changestatus.ChangeStatusFragmentModule;
import com.techsales.taskmanager.di.scope.DialogScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class DialogBindingModule {
    @DialogScope
    @ContributesAndroidInjector(modules = ChangeStatusFragmentModule.class)
    abstract ChangeStatusFragment changeStatusFragment();
}
