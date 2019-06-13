package com.techsales.taskmanager.di;

import com.techsales.taskmanager.contacts.ContactFragmentModule;
import com.techsales.taskmanager.contacts.ContactsFragment;
import com.techsales.taskmanager.dashboard.DashboardFragment;
import com.techsales.taskmanager.dashboard.DashboardFragmentModule;
import com.techsales.taskmanager.di.scope.FragmentScope;
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
}
