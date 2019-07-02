package com.techsales.taskmanager.data.model.api.chooseemployee;

import com.google.gson.annotations.SerializedName;

public class ChooseEmployeeResponse {
    private int id;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("user_type")
    private int userType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
