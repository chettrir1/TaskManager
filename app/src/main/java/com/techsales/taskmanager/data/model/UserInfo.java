package com.techsales.taskmanager.data.model;

import com.techsales.taskmanager.data.model.viewmodel.login.LoginViewModel;
import com.techsales.taskmanager.data.model.viewmodel.profile.ProfileViewModel;

public class UserInfo {

    private String id;
    private String full_name;
    private String contact;
    private String email;
    private String permanent_address;
    private String temporary_address;
    private String profile_image;
    private String citizenship_image;
    private String user_name;
    private String last_login_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
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

    public String getPermanent_address() {
        return permanent_address;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }

    public String getTemporary_address() {
        return temporary_address;
    }

    public void setTemporary_address(String temporary_address) {
        this.temporary_address = temporary_address;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getCitizenship_image() {
        return citizenship_image;
    }

    public void setCitizenship_image(String citizenship_image) {
        this.citizenship_image = citizenship_image;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getLast_login_at() {
        return last_login_at;
    }

    public void setLast_login_at(String last_login_at) {
        this.last_login_at = last_login_at;
    }

    public static LoginViewModel mapToViewModel(UserInfo userInfo){
        return new LoginViewModel(userInfo);
    }

    public static ProfileViewModel mapToProfileViewModel(UserInfo userInfo){
        return new ProfileViewModel(userInfo);
    }
}
