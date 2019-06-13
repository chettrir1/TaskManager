package com.techsales.taskmanager.notes;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AddNotesFragmentModule {

    @FragmentScope
    @Provides
    AddNotesContract.View provideFragment(AddNotesFragment fragment) {
        return fragment;
    }

    @FragmentScope
    @Provides
    AddNotesContract.Presenter providePresenter(TaskManagerComponent component, AddNotesContract.View view) {
        return new AddNotesPresenter(component, view);
    }
}
