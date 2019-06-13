package com.techsales.taskmanager.contacts;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactFragmentModule {

    @FragmentScope
    @Provides
    ContactsContract.View provideFragment(ContactsFragment fragment) {
        return fragment;
    }

    @FragmentScope
    @Provides
    ContactsContract.Presenter providePresenter(TaskManagerComponent component, ContactsContract.View view) {
        return new ContactsPresenter(component, view);
    }
}
