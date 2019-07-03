package com.techsales.taskmanager.createtask.chooseemployee;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;
import com.techsales.taskmanager.data.model.viewmodel.chooseemployee.ChooseEmployeeViewModel;

import java.util.List;

public interface ChooseEmployeeContract {
    interface View extends BaseView<Presenter> {
        void showProgress();

        void showLoadingError(String message);

        void showNoNetworkAvailable();

        void showLoadingSuccess(List<ChooseEmployeeViewModel> viewModels);

        void onEmployeeItemClick(ChooseEmployeeViewModel items, int position, boolean cbAssignTo);
    }

    interface Presenter extends BasePresenter, ChooseEmployeeRecyclerAdapter.ChooseEmployeeItemsClickListener {

    }
}
