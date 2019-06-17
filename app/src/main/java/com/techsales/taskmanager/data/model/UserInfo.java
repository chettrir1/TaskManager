package com.techsales.taskmanager.data.model;

import com.google.gson.annotations.SerializedName;
import com.techsales.taskmanager.data.model.viewmodel.login.LoginViewModel;
import com.techsales.taskmanager.data.model.viewmodel.profile.ProfileViewModel;

public class UserInfo {

    private String id;
    @SerializedName("full_name")
    private String fullName;
    private String contact;
    private String email;
    @SerializedName("permanent_address")
    private String permanentAddress;
    @SerializedName("temporary_address")
    private String temporaryAddress;
    @SerializedName("profile_image")
    private String profileImage;
    @SerializedName("citizenship_image")
    private String citizenshipImage;
    @SerializedName("user_name")
    private String userName;
    @SerializedName("last_login_at")
    private String lastLoginAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(String temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getCitizenshipImage() {
        return citizenshipImage;
    }

    public void setCitizenshipImage(String citizenshipImage) {
        this.citizenshipImage = citizenshipImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(String lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public static LoginViewModel mapToViewModel(UserInfo userInfo) {
        return new LoginViewModel(userInfo);
    }

    public static ProfileViewModel mapToProfileViewModel(UserInfo userInfo) {
        return new ProfileViewModel(userInfo);
    }
}
