package com.techsales.taskmanager.data.remote;

import com.techsales.taskmanager.data.model.UserInfo;
import com.techsales.taskmanager.data.model.api.BaseResponse;
import com.techsales.taskmanager.data.model.dashboard.bottom.BaseTasksResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RemoteRepo {

    @POST("login")
    Single<BaseResponse<UserInfo>> requestLogin(
            @Body Map<String, Object> params);

    @GET("task")
    Single<BaseResponse<BaseTasksResponse>> getNewTasks(
            @Query("user_id") String user_id);


}
