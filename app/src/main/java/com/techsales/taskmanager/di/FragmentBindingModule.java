package com.techsales.taskmanager.di;

import com.techsales.taskmanager.contacts.ContactFragmentModule;
import com.techsales.taskmanager.contacts.ContactsFragment;
import com.techsales.taskmanager.dashboard.DashboardFragment;
import com.techsales.taskmanager.dashboard.DashboardFragmentModule;
import com.techsales.taskmanager.di.scope.FragmentScope;
import com.techsales.taskmanager.notes.AddNotesFragment;
import com.techsales.taskmanager.notes.AddNotesFragmentModule;
import com.techsales.taskmanager.notes.NoteListFragment;
import com.techsales.taskmanager.notes.NoteListFragmentModule;
import com.techsales.taskmanager.profile.ProfileFragment;
import com.techsales.taskmanager.profile.ProfileFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBindingModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = DashboardFragmentModule.class)
    abstract DashboardFragment dashboardFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = ProfileFragmentModule.class)
    abstract ProfileFragment profileFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = ContactFragmentModule.class)
    abstract ContactsFragment contactsFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = AddNotesFragmentModule.class)
    abstract AddNotesFragment addNotesFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = NoteListFragmentModule.class)
    abstract NoteListFragment noteListFragment();

}
