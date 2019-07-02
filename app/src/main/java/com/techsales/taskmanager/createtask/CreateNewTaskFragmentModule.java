package com.techsales.taskmanager.createtask;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class CreateNewTaskFragmentModule {

    @FragmentScope
    @Provides
    CreateNewTaskContract.View provideFragment(CreateNewTaskFragment fragment) {
        return fragment;
    }

    @FragmentScope
    @Provides
    CreateNewTaskContract.Presenter providePresenter(TaskManagerComponent component, CreateNewTaskContract.View view) {
        return new CreateNewTaskPresenter(component, view);
    }
}
