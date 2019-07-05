package com.techsales.taskmanager.createtask.chooseemployee;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;
import com.techsales.taskmanager.data.model.createtask.AboutTask;
import com.techsales.taskmanager.data.model.viewmodel.chooseemployee.ChooseEmployeeViewModel;

import org.json.JSONObject;

import java.util.List;

public interface ChooseEmployeeContract {
    interface View extends BaseView<Presenter> {
        void showProgress();

        void showLoadingError(String message);

        void showNoNetworkAvailable();

        void showLoadingSuccess(List<ChooseEmployeeViewModel> viewModels);

        void onEmployeeItemClick(ChooseEmployeeViewModel items, boolean isChecked);

        void showTaskAssignProgress();

        void showTaskAssignError(String message);

        void showTaskAssignSuccess(String message);

        void showTaskAssignNetworkError();
    }

    interface Presenter extends BasePresenter, ChooseEmployeeRecyclerAdapter.ChooseEmployeeItemsClickListener {
        void getDataWithBundle(AboutTask aboutTask);

        void assignTask(JSONObject staffs);
    }
}
