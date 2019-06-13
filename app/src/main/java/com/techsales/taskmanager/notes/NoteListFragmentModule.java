package com.techsales.taskmanager.notes;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class NoteListFragmentModule {

    @FragmentScope
    @Provides
    NoteListContract.View provideFragment(NoteListFragment fragment) {
        return fragment;
    }

    @FragmentScope
    @Provides
    NoteListContract.Presenter providePresenter(TaskManagerComponent component, NoteListContract.View view) {
        return new NoteListPresenter(component, view);
    }
}
