package com.techsales.taskmanager.contacts;

import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.error.FailedResponseException;
import com.techsales.taskmanager.data.error.NetworkNotAvailableException;
import com.techsales.taskmanager.data.model.api.contacts.BaseContactsResponse;
import com.techsales.taskmanager.data.model.api.contacts.ContactsResponse;
import com.techsales.taskmanager.data.model.viewmodel.contacts.ContactsviewModel;
import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class ContactsPresenter implements ContactsContract.Presenter {

    private final TaskManagerComponent component;
    private ContactsContract.View view;
    private Disposable disposable;

    ContactsPresenter(TaskManagerComponent component, ContactsContract.View view) {
        this.component = component;
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        Commons.dispose(disposable);
    }

    @Override
    public void getAllContacts() {
        disposable = component.data().getAllContacts()
                .subscribe((List<ContactsResponse> contactsResponses) -> {
                    if (Commons.isEmpty(contactsResponses)) {
                        List<ContactsviewModel> contactsviewModels;
                        contactsviewModels = BaseContactsResponse.mapToViewModel(component.context(), contactsResponses);
                        view.showLoadingSuccess(contactsviewModels);
                    }
                }, throwable -> {
                    if (throwable instanceof FailedResponseException)
                        view.showLoadingError(throwable.getMessage());
                    else if (throwable instanceof NetworkNotAvailableException)
                        view.showNoNetworkAvailable();
                    else
                        view.showLoadingError(component.context().getString(R.string.server_error));
                });
    }

    @Override
    public void onContactsItemClicked(ContactsviewModel items, int position) {
        view.onContactsItemClick(items, position);
    }
}
