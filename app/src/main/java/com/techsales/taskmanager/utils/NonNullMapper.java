package com.techsales.taskmanager.utils;

import com.techsales.taskmanager.data.error.FailedResponseException;
import com.techsales.taskmanager.data.model.api.BaseResponse;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class NonNullMapper<T> implements Function<BaseResponse<T>, Single<T>> {
    @Override
    public Single<T> apply(@NonNull BaseResponse<T> baseResponse) throws Exception {
        if (baseResponse.getError())
            return Single.error(new FailedResponseException(baseResponse.getError(), baseResponse.getMessage()));
        T item = baseResponse.getData();
        if (item == null)
            return Single.error(new NullPointerException("BaseResponse.Response == null"));
        else
            return Single.just(item);
    }
}