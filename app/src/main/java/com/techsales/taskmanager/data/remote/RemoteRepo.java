package com.techsales.taskmanager.data.remote;

import com.techsales.taskmanager.data.model.login.UserInfo;
import com.techsales.taskmanager.data.model.api.BaseResponse;
import com.techsales.taskmanager.data.model.api.dashboard.BaseTasksResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RemoteRepo {
     String BASE_IMAGE_URL = "http://117.121.237.226:83/task/public/storage/";

    @POST("login")
    Single<BaseResponse<UserInfo>> requestLogin(
            @Body Map<String, Object> params);

    @GET("task")
    Single<BaseResponse<BaseTasksResponse>> getNewTasks(
            @Query("user_id") String user_id);


}
