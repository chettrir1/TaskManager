package com.techsales.taskmanager.notification.viewnotification;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewNotificationFragmentModule {

    @FragmentScope
    @Provides
    ViewNotificationContract.View provideFragment(ViewNotificationFragment fragment) {
        return fragment;
    }

    @FragmentScope
    @Provides
    ViewNotificationContract.Presenter providePresenter(TaskManagerComponent component, ViewNotificationContract.View view) {
        return new ViewNotificationPresenter(component, view);
    }
}
