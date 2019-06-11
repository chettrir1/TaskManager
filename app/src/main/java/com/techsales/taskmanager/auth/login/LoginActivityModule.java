package com.techsales.taskmanager.auth.login;

import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {

    @Provides
    @ActivityScope
    LoginContract.View provideLoginActivity(LoginActivity loginActivity) {
        return loginActivity;
    }

    @Provides
    @ActivityScope
    LoginContract.Presenter providePresenter(TaskManagerComponent component, LoginContract.View view) {
        return new LoginPresenter(component, view);
    }
}
