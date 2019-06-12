package com.techsales.taskmanager.data.remote;

import com.techsales.taskmanager.data.model.UserInfo;
import com.techsales.taskmanager.data.model.api.BaseResponse;
import com.techsales.taskmanager.data.model.dashboard.bottom.BottomRecyclerItemsData;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RemoteRepo {

    @POST("login")
    Single<BaseResponse<UserInfo>> requestLogin(
            @Body Map<String, Object> params);

    @POST("task")
    Single<BaseResponse<BottomRecyclerItemsData>> getNewTasks(
            @Body Map<String, Object> params);


}
