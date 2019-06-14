package com.techsales.taskmanager.data.local;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.google.gson.Gson;
import com.techsales.taskmanager.data.model.UserInfo;
import com.techsales.taskmanager.data.model.notes.Notes;
import com.techsales.taskmanager.database.NotesDatabase;
import com.techsales.taskmanager.utils.Commons;
import com.techsales.taskmanager.utils.Config;

import java.util.List;

import javax.inject.Inject;

public class LocalRepo {

    private static final String USER_INFO = "_user_info";
    private static final String USERNAME = "_username";
    private static final String REMEMBER_ME = "_remember_me";
    private static final String PASSWORD = "_password";

    private final SharedPreferences sharedPreferences;
    private final Gson gson;
    private UserInfo cachedUserInfo;
    private String cachedUsername;
    private String cachedPassword;
    private boolean cachedRememberStatus;

    private static NotesDatabase noteDatabase;

    @Inject
    LocalRepo(Context context, Gson gson) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.gson = gson;
        noteDatabase = Room.databaseBuilder(context, NotesDatabase.class, Config.DATABASE_NAME).build();
    }

    public void insertTask(String title,
                           String description) {
        Notes note = new Notes();
        note.setTitle(title);
        note.setDescription(description);
        note.setCreatedAt(Commons.getCurrentDateTime());
        note.setModifiedAt(Commons.getCurrentDateTime());

        insertTask(note);
    }

    private static void insertTask(final Notes note) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDatabase.notesDao().insertTask(note);
                return null;
            }
        }.execute();
    }

    public static void updateTask(final Notes note) {
        note.setModifiedAt(Commons.getCurrentDateTime());

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDatabase.notesDao().updateTask(note);
                return null;
            }
        }.execute();
    }

    public static void deleteTask(final int id) {
        final LiveData<Notes> task = getTask(id);
        if (task != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    noteDatabase.notesDao().deleteTask(task.getValue());
                    return null;
                }
            }.execute();
        }
    }

    public static void deleteTask(final Notes note) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDatabase.notesDao().deleteTask(note);
                return null;
            }
        }.execute();
    }

    private static LiveData<Notes> getTask(int id) {
        return noteDatabase.notesDao().getTask(id);
    }

    public LiveData<List<Notes>> getTasks() {
        return noteDatabase.notesDao().fetchAllTasks();
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
