package com.techsales.taskmanager.data.model.dashboard.bottom;

import com.google.gson.annotations.SerializedName;

public class Author {
    @SerializedName("full_name")
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
