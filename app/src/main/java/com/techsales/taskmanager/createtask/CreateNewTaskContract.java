package com.techsales.taskmanager.createtask;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;

import java.util.List;

public interface CreateNewTaskContract {

    interface View extends BaseView<Presenter> {

        void setPrioritySpinner(List<String> priority);

        void setTaskTypeSpinner(List<String> type);
    }

    interface Presenter extends BasePresenter {

    }
}
