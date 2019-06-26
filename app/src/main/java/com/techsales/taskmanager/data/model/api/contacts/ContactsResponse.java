package com.techsales.taskmanager.data.model.api.contacts;

import com.google.gson.annotations.SerializedName;

public class ContactsResponse {
    private String id;
    private String name;
    private String department;
    private String post;
    private String contact;

    @SerializedName("ext_number")
    private String extNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getExtNumber() {
        return extNumber;
    }

    public void setExtNumber(String extNumber) {
        this.extNumber = extNumber;
    }
}
