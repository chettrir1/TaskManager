package com.techsales.taskmanager.notification;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class NotificationFragmentModule {

    @FragmentScope
    @Provides
    NotificationContract.View provideFragment(NotificationFragment fragment) {
        return fragment;
    }

    @FragmentScope
    @Provides
    NotificationContract.Presenter providePresenter(TaskManagerComponent component, NotificationContract.View view) {
        return new NotificationPresenter(component, view);
    }
}
