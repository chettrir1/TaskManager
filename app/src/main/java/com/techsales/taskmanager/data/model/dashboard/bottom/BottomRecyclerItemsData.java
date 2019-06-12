package com.techsales.taskmanager.data.model.dashboard.bottom;

public class BottomRecyclerItemsData {
    private String last_login_at;
    private BottomRecyclerItemsDataWhere_task[] where_task;
    private String join_date;
    private String user_name;
    private String created_at;
    private String citizenship_image;
    private String firebase_token;
    private String profile_image;
    private String full_name;
    private int user_type;
    private String updated_at;
    private int id;
    private String linkedin_link;
    private String department;

    public String getLast_login_at() {
        return last_login_at;
    }

    public void setLast_login_at(String last_login_at) {
        this.last_login_at = last_login_at;
    }

    public BottomRecyclerItemsDataWhere_task[] getWhere_task() {
        return where_task;
    }

    public void setWhere_task(BottomRecyclerItemsDataWhere_task[] where_task) {
        this.where_task = where_task;
    }

    public String getJoin_date() {
        return join_date;
    }

    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCitizenship_image() {
        return citizenship_image;
    }

    public void setCitizenship_image(String citizenship_image) {
        this.citizenship_image = citizenship_image;
    }

    public String getFirebase_token() {
        return firebase_token;
    }

    public void setFirebase_token(String firebase_token) {
        this.firebase_token = firebase_token;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinkedin_link() {
        return linkedin_link;
    }

    public void setLinkedin_link(String linkedin_link) {
        this.linkedin_link = linkedin_link;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
