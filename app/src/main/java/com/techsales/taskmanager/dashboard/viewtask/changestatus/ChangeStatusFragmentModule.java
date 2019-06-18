package com.techsales.taskmanager.dashboard.viewtask.changestatus;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.di.scope.DialogScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ChangeStatusFragmentModule {

    @DialogScope
    @Provides
    ChangeStatusContract.View provideFragment(ChangeStatusFragment fragment) {
        return fragment;
    }

    @DialogScope
    @Provides
    ChangeStatusContract.Presenter providePresenter(TaskManagerComponent component, ChangeStatusContract.View view) {
        return new ChangeStatusPresenter(component, view);
    }
}
