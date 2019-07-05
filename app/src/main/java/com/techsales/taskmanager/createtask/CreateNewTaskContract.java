package com.techsales.taskmanager.createtask;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;
import com.techsales.taskmanager.data.model.createtask.AboutTask;

import java.util.List;

public interface CreateNewTaskContract {

    interface View extends BaseView<Presenter> {

        void setPrioritySpinner(List<String> priority);

        void setTaskTypeSpinner(List<String> type);

        void errorEmptyTitleVisible();

        void errorEmptyDeadlineVisible();

        void errorClientNameVisible();

        void errorEmptyTitleInvisible();

        void errorEmptyDeadlineInvisible();

        void errorClientNameInvisible();

        void validationSucces(AboutTask tasks);
    }

    interface Presenter extends BasePresenter {

        void validateNewTask(String taskTitle, String taskPriority, String clientName,
                             String taskDeadline, String clientPhone, String taskDescription);
    }
}
