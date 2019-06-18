package com.techsales.taskmanager.dashboard.viewtask;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;
import com.techsales.taskmanager.data.model.viewmodel.taskdetails.TaskDetailsViewModel;
import com.techsales.taskmanager.data.model.viewtask.TaskDetails;

public interface ViewTaskContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        TaskDetailsViewModel getTaskDetailsViewModel(TaskDetails taskDetails);
    }
}
