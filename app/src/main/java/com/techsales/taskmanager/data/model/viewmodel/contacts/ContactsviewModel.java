package com.techsales.taskmanager.data.model.viewmodel.contacts;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.techsales.taskmanager.data.model.api.contacts.ContactsResponse;

public class ContactsviewModel extends BaseObservable {
    private Context context;
    private ContactsResponse items;

    public ContactsviewModel(Context context, ContactsResponse items) {
        this.context = context;
        this.items = items;
    }

    @Bindable
    public String getShortName() {
        char shortName = items.getName().charAt(0);
        return String.valueOf(shortName);
    }

    @Bindable
    public String getNameAndPost() {
        return String.format("%s (%s) ", items.getName(), items.getPost());
    }

    @Bindable
    public String getDepartment() {
        return items.getDepartment();
    }

    @Bindable
    public String getPhone() {
        return items.getContact();
    }

    @Bindable
    public String getPhoneAndExtension() {
        return String.format("%s (%s) ", items.getContact(), items.getExtNumber());
    }
}
