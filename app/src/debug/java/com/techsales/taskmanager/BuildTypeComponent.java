package com.techsales.taskmanager;

import com.techsales.taskmanager.di.scope.TaskManagerScope;

import dagger.Subcomponent;

@TaskManagerScope
@Subcomponent
public interface BuildTypeComponent {

    BuildTypeConfig buildTypeConfig();
}
