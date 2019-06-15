package com.techsales.taskmanager.data;

import androidx.lifecycle.LiveData;

import com.techsales.taskmanager.data.local.LocalRepo;
import com.techsales.taskmanager.data.model.UserInfo;
import com.techsales.taskmanager.data.model.api.BaseResponse;
import com.techsales.taskmanager.data.model.dashboard.bottom.BaseTasksResponse;
import com.techsales.taskmanager.data.model.dashboard.bottom.WhereTask;
import com.techsales.taskmanager.data.model.notes.Notes;
import com.techsales.taskmanager.data.remote.RemoteRepo;
import com.techsales.taskmanager.utils.NonNullMapper;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class Data {
    private final LocalRepo localRepo;
    private final RemoteRepo remoteRepo;

    @Inject
    public Data(LocalRepo localRepo, RemoteRepo remoteRepo) {
        this.localRepo = localRepo;
        this.remoteRepo = remoteRepo;
    }

    public boolean isLoggedIn() {
        return localRepo.getCachedUserInfo() != null;
    }

    public void logout() {
        localRepo.logout();
    }

    public UserInfo savedUserInfo() {
        return localRepo.getCachedUserInfo();
    }

    public String savedUsername() {
        return localRepo.getCachedUsername();
    }

    public String savedPassword() {
        return localRepo.getCachedPassword();
    }

    public boolean savedRememberStatus() {
        return localRepo.getCachedRememberStatus();
    }

    public void rememberChecked(String username, String password, boolean isChecked) {
        localRepo.setUsernamePassowrd(username, password, isChecked);
    }

    public void rememberUnchecked() {
        localRepo.removeUsernamePassword();
    }

    public void insertNotes(String title, String description) {
        localRepo.insertTask(title, description);
    }

    public void updateNotes(Notes notes) {
        LocalRepo.updateTask(notes);
    }

    public void deleteTask(int id) {
        LocalRepo.deleteTask(id);
    }

    public void deleteAllTask(Notes notes) {
        LocalRepo.deleteTask(notes);
    }

    public LiveData<List<Notes>> getAllNotes() {
        return localRepo.getTasks();
    }

    public Single<UserInfo> requestLogin(String username, String password, String token) {
        HashMap<String, Object> params = new HashMap<>(3);
        params.put("username", username);
        params.put("password", password);
        params.put("firebase_token", token);
        return remoteRepo.requestLogin(params)
                .flatMap(new NonNullMapper<>())
                .doOnSuccess(userInfo -> {
                    if (userInfo != null)
                        localRepo.setUserInfo(userInfo);
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Single<List<WhereTask>> getAllTasks(String user_id) {
        return remoteRepo.getNewTasks(user_id)
                .flatMap(new NonNullMapper<>())
                .flatMap(baseTasksResponse -> Single.just(baseTasksResponse.getwhereTasks()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

}
