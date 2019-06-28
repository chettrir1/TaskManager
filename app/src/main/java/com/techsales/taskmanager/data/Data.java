package com.techsales.taskmanager.data;

import com.techsales.taskmanager.data.local.LocalRepo;
import com.techsales.taskmanager.data.local.database.DatabaseRepo;
import com.techsales.taskmanager.data.model.api.BaseResponse;
import com.techsales.taskmanager.data.model.api.contacts.ContactsResponse;
import com.techsales.taskmanager.data.model.api.dashboard.BaseTasksResponse;
import com.techsales.taskmanager.data.model.api.notification.NotificationResponse;
import com.techsales.taskmanager.data.model.api.status.BaseStatusResponse;
import com.techsales.taskmanager.data.model.api.status.StatusResponse;
import com.techsales.taskmanager.data.model.login.UserInfo;
import com.techsales.taskmanager.data.model.api.dashboard.WhereTask;
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
import okhttp3.MultipartBody;

@Singleton
public class Data {
    private final LocalRepo localRepo;
    private final RemoteRepo remoteRepo;
    private final DatabaseRepo databaseRepo;

    @Inject
    public Data(LocalRepo localRepo, RemoteRepo remoteRepo, DatabaseRepo databaseRepo) {
        this.localRepo = localRepo;
        this.remoteRepo = remoteRepo;
        this.databaseRepo = databaseRepo;
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
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Single<BaseResponse> requestChangeStatus(String taskId, String taskStatus, String remarks) {
        HashMap<String, Object> params = new HashMap<>(3);
        params.put("task_id", taskId);
        params.put("status", taskStatus);
        params.put("remarks", remarks);

        return remoteRepo.requestChangeStatus(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BaseResponse> uploadCompletedTask(String taskId, String taskStatus,
                                                    String remarks, MultipartBody.Part part) {

        return remoteRepo.uploadCompletedTask(taskId, taskStatus, remarks, part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<WhereTask>> getAllTasks(String user_id) {
        return remoteRepo.getNewTasks(user_id)
                .flatMap(new NonNullMapper<>())
                .flatMap(baseTasksResponse -> Single.just(baseTasksResponse.getwhereTasks()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<NotificationResponse>> getAllNotification(String user_id) {
        return remoteRepo.getNotifications(user_id)
                .flatMap(baseNotificationResponse -> Single.just(baseNotificationResponse.getNotificationResponses()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<ContactsResponse>> getAllContacts() {
        return remoteRepo.getContacts()
                .flatMap(baseContactsResponse -> Single.just(baseContactsResponse.getContactsResponses()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BaseStatusResponse> requestStatus(int page, int limit, String userId, int status) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("limit", limit);
        params.put("user_id", userId);
        params.put("status", status);
        return remoteRepo.requestStatus(params)
                .map(BaseResponse::getData)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Long> insertNotes(Notes notes) {
        return databaseRepo.insertToNotes(notes)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<Notes>> getAllNotes() {
        return databaseRepo.getAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
