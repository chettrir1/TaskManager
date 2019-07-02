package com.techsales.taskmanager.createtask.chooseemployee;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ChooseEmployeeFragmentModule {

    @FragmentScope
    @Provides
    ChooseEmployeeContract.View provideFragment(ChooseEmployeeFragment fragment) {
        return fragment;
    }

    @FragmentScope
    @Provides
    ChooseEmployeeContract.Presenter providePresenter(TaskManagerComponent component, ChooseEmployeeContract.View view) {
        return new ChooseEmployeePresenter(component, view);
    }
}
