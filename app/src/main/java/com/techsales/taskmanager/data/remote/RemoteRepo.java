package com.techsales.taskmanager.data.remote;

import com.techsales.taskmanager.data.model.api.BaseResponse;
import com.techsales.taskmanager.data.model.api.chooseemployee.BaseChooseEmployeeResponse;
import com.techsales.taskmanager.data.model.api.contacts.BaseContactsResponse;
import com.techsales.taskmanager.data.model.api.dashboard.BaseTasksResponse;
import com.techsales.taskmanager.data.model.api.notification.BaseNotificationResponse;
import com.techsales.taskmanager.data.model.api.status.BaseStatusResponse;
import com.techsales.taskmanager.data.model.dashboard.taskcount.TaskCount;
import com.techsales.taskmanager.data.model.login.UserInfo;

import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RemoteRepo {
    String BASE_IMAGE_URL = "http://117.121.237.226:83/task/public/storage/";

    @POST("login")
    Single<BaseResponse<UserInfo>> requestLogin(
            @Body Map<String, Object> params);

    @GET("task/count")
    Single<BaseResponse<TaskCount>> getTaskCount(
            @Query("user_id") String user_id);

    @GET("task")
    Single<BaseResponse<BaseTasksResponse>> getNewTasks(
            @Query("user_id") String user_id);

    @POST("change/status")
    Single<BaseResponse> requestChangeStatus(
            @Body Map<String, Object> params);

    @Multipart
    @POST("change/status")
    Single<BaseResponse> uploadCompletedTask(
            @Query("task_id") String task_id,
            @Query("status") String status,
            @Query("remarks") String remarks,
            @Part MultipartBody.Part file);

    @GET("task/notification")
    Single<BaseNotificationResponse> getNotifications(
            @Query("user_id") String user_id);

    @GET("telephone/list")
    Single<BaseContactsResponse> getContacts();

    @POST("task/status")
    Single<BaseResponse<BaseStatusResponse>> requestStatus(
            @Body Map<String, Object> params);

    @GET("user/list")
    Single<BaseChooseEmployeeResponse> getEmployeeList();

    @POST("task/store")
    Single<BaseResponse> assignTask(
            @Body Map<String, Object> params);

    @POST("logout")
    Single<BaseResponse> requestLogout(
            @Body Map<String, Object> params);

}
