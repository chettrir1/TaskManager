package com.techsales.taskmanager.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.techsales.taskmanager.data.model.login.UserInfo;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class LocalRepo {

    private static final String USER_INFO = "_user_info";
    private static final String USERNAME = "_username";
    private static final String REMEMBER_ME = "_remember_me";
    private static final String PASSWORD = "_password";

    private final SharedPreferences sharedPreferences;
    private UserInfo cachedUserInfo;
    private String cachedUsername;
    private String cachedPassword;
    private boolean cachedRememberStatus;

    private final Gson gson;
    private final Context context;

    @Inject
    LocalRepo(Gson gson, Context context) {
        this.gson = gson;
        this.context = context;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setUserInfo(@NonNull UserInfo userInfo) {
        sharedPreferences.edit().putString(USER_INFO, gson.toJson(userInfo)).apply();
        cachedUserInfo = userInfo;
    }

    public void setUsernamePassowrd(String username, String password, boolean isChecked) {
        sharedPreferences.edit().putString(USERNAME, username)
                .putString(PASSWORD, password).putBoolean(REMEMBER_ME, isChecked).apply();
        cachedUsername = username;
        cachedPassword = password;
        cachedRememberStatus = isChecked;
    }

    public UserInfo getCachedUserInfo() {
        if (cachedUserInfo == null) {
            String userInfoS = sharedPreferences.getString(USER_INFO, null);
            if (!TextUtils.isEmpty(userInfoS)) {
                cachedUserInfo = gson.fromJson(userInfoS, UserInfo.class);
            }
        }
        return cachedUserInfo;
    }

    public String getCachedUsername() {
        if (cachedUsername == null) {
            String username = sharedPreferences.getString(USERNAME, null);
            if (!TextUtils.isEmpty(username)) {
                cachedUsername = username;
            }
        }
        return cachedUsername;
    }

    public String getCachedPassword() {
        if (cachedPassword == null) {
            String password = sharedPreferences.getString(PASSWORD, null);
            if (!TextUtils.isEmpty(password)) {
                cachedPassword = password;
            }
        }
        return cachedPassword;
    }

    public boolean getCachedRememberStatus() {
        if (!cachedRememberStatus) {
            boolean remember_status = sharedPreferences.getBoolean(REMEMBER_ME, false);
            if (!remember_status) {
                cachedRememberStatus = false;
            }
        }
        return cachedRememberStatus;
    }

    public void removeUsernamePassword() {
        this.cachedUsername = null;
        this.cachedPassword = null;
        sharedPreferences.edit().remove(USERNAME).remove(PASSWORD).apply();
    }

    public void logout() {
        this.cachedUserInfo = null;
        sharedPreferences.edit().clear().apply();
    }
}
