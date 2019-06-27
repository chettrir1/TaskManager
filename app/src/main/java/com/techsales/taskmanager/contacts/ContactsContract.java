package com.techsales.taskmanager.contacts;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;
import com.techsales.taskmanager.data.model.viewmodel.contacts.ContactsviewModel;

import java.util.List;

public interface ContactsContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void showLoadingError(String message);

        void showLoadingSuccess(List<ContactsviewModel> contactsviewModels);

        void showNoNetworkAvailable();

        void onContactsItemClick(ContactsviewModel items, int position);
    }

    interface Presenter extends BasePresenter, ContactsRecyclerAdapter.ContactsItemClickListener {
        void getAllContacts();
    }
}
