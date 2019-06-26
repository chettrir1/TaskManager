package com.techsales.taskmanager.data.model.api.contacts;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.techsales.taskmanager.data.model.viewmodel.contacts.ContactsviewModel;

import java.util.ArrayList;
import java.util.List;

public class BaseContactsResponse {

    @SerializedName("data")
    private List<ContactsResponse> contactsResponses;

    public List<ContactsResponse> getContactsResponses() {
        return contactsResponses;
    }

    public void setContactsResponses(List<ContactsResponse> contactsResponses) {
        this.contactsResponses = contactsResponses;
    }

    public static List<ContactsviewModel> mapToViewModel(Context context, List<ContactsResponse> items) {
        final int count = items.size();
        ArrayList<ContactsviewModel> viewModels = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            viewModels.add(new ContactsviewModel(context, items.get(i)));
        }
        return viewModels;
    }
}
