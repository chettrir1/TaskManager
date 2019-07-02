package com.techsales.taskmanager.dashboard.container;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class DashboardActivityModule {

    /* @Provides
     @ActivityScope
     Activity provideActivity(DashboardActivity activity) {
         return activity;
     }*/
    @Provides
    @ActivityScope
    DashboardContract.View provideDashboardActivity(DashboardActivity activity) {
        return activity;
    }

    @Provides
    @ActivityScope
    DashboardContract.Presenter providePresenter(TaskManagerComponent component, DashboardContract.View view) {
        return new DashboardPresenter(component, view);
    }
}
