package com.techsales.taskmanager.data.remote;

import com.techsales.taskmanager.data.model.api.notification.BaseNotificationResponse;
import com.techsales.taskmanager.data.model.login.UserInfo;
import com.techsales.taskmanager.data.model.api.BaseResponse;
import com.techsales.taskmanager.data.model.api.dashboard.BaseTasksResponse;

import java.util.List;
import java.util.Map;

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

}
