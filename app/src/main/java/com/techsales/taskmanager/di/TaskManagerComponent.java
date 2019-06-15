package com.techsales.taskmanager.di;

import android.content.Context;

import com.google.gson.Gson;
import com.techsales.taskmanager.BuildTypeComponent;
import com.techsales.taskmanager.TaskManagerApp;
import com.techsales.taskmanager.data.Data;
import com.techsales.taskmanager.data.remote.DataModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {CommonsModule.class,
        DataModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})

public interface TaskManagerComponent extends AndroidInjector<TaskManagerApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(TaskManagerApp taskManagerApp);

        TaskManagerComponent build();
    }

    Context context();

    Gson gson();

    BuildTypeComponent buildType();

    Data data();
}
