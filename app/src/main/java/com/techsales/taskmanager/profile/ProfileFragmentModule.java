package com.techsales.taskmanager.profile;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileFragmentModule {

    @FragmentScope
    @Provides
    ProfileContract.View provideFragment(ProfileFragment fragment) {
        return fragment;
    }

    @FragmentScope
    @Provides
    ProfileContract.Presenter providePresenter(TaskManagerComponent component, ProfileContract.View view) {
        return new ProfilePresenter(component, view);
    }
}
