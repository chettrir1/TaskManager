package com.techsales.taskmanager.di;

import com.techsales.taskmanager.contacts.ContactFragmentModule;
import com.techsales.taskmanager.contacts.ContactsFragment;
import com.techsales.taskmanager.createtask.CreateNewTaskFragment;
import com.techsales.taskmanager.createtask.CreateNewTaskFragmentModule;
import com.techsales.taskmanager.dashboard.DashboardFragment;
import com.techsales.taskmanager.dashboard.DashboardFragmentModule;
import com.techsales.taskmanager.dashboard.viewtask.ViewTaskFragment;
import com.techsales.taskmanager.dashboard.viewtask.ViewTaskFragmentModule;
import com.techsales.taskmanager.dashboard.viewtask.changestatus.ChangeStatusFragment;
import com.techsales.taskmanager.dashboard.viewtask.changestatus.ChangeStatusFragmentModule;
import com.techsales.taskmanager.di.scope.FragmentScope;
import com.techsales.taskmanager.notes.AddNotesFragment;
import com.techsales.taskmanager.notes.AddNotesFragmentModule;
import com.techsales.taskmanager.notes.NoteListFragment;
import com.techsales.taskmanager.notes.NoteListFragmentModule;
import com.techsales.taskmanager.notification.NotificationFragment;
import com.techsales.taskmanager.notification.NotificationFragmentModule;
import com.techsales.taskmanager.notification.viewnotification.ViewNotificationFragment;
import com.techsales.taskmanager.notification.viewnotification.ViewNotificationFragmentModule;
import com.techsales.taskmanager.profile.ProfileFragment;
import com.techsales.taskmanager.profile.ProfileFragmentModule;
import com.techsales.taskmanager.status.StatusFragment;
import com.techsales.taskmanager.status.StatusFragmentModule;

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

    @FragmentScope
    @ContributesAndroidInjector(modules = ViewTaskFragmentModule.class)
    abstract ViewTaskFragment viewTaskFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = ChangeStatusFragmentModule.class)
    abstract ChangeStatusFragment changeStatusFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = NotificationFragmentModule.class)
    abstract NotificationFragment notificationFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = ViewNotificationFragmentModule.class)
    abstract ViewNotificationFragment viewNotificationFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = StatusFragmentModule.class)
    abstract StatusFragment statusFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = CreateNewTaskFragmentModule.class)
    abstract CreateNewTaskFragment createNewTaskFragment();
}
