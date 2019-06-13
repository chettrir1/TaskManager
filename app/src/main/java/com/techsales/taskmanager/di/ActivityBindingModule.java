package com.techsales.taskmanager.di;

import com.techsales.taskmanager.auth.login.LoginActivity;
import com.techsales.taskmanager.auth.login.LoginActivityModule;
import com.techsales.taskmanager.contacts.container.ContactsActivity;
import com.techsales.taskmanager.contacts.container.ContactsActivityModule;
import com.techsales.taskmanager.dashboard.container.DashboardActivity;
import com.techsales.taskmanager.dashboard.container.DashboardActivityModule;
import com.techsales.taskmanager.di.scope.ActivityScope;
import com.techsales.taskmanager.notes.AddNotesFragmentModule;
import com.techsales.taskmanager.notes.container.AddNotesActivity;
import com.techsales.taskmanager.notes.container.AddNotesActivityModule;
import com.techsales.taskmanager.notes.container.NoteListActivity;
import com.techsales.taskmanager.notes.container.NoteListActivityModule;
import com.techsales.taskmanager.profile.container.ProfileActivity;
import com.techsales.taskmanager.profile.container.ProfileActivityModule;

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

    @ActivityScope
    @ContributesAndroidInjector(modules = {ProfileActivityModule.class, FragmentBindingModule.class})
    abstract ProfileActivity profileActivity();


    @ActivityScope
    @ContributesAndroidInjector(modules = {ContactsActivityModule.class, FragmentBindingModule.class})
    abstract ContactsActivity contactsActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {AddNotesActivityModule.class, FragmentBindingModule.class})
    abstract AddNotesActivity addNotesActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {NoteListActivityModule.class, FragmentBindingModule.class})
    abstract NoteListActivity noteListActivity();
}
