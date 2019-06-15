package com.techsales.taskmanager.data.remote;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.techsales.taskmanager.data.error.NetworkNotAvailableException;
import com.techsales.taskmanager.utils.Commons;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class ApiInterceptor implements Interceptor {
    private Context context;

    @Inject
    ApiInterceptor(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        if (!Commons.isNetworkAvailable(context)) {
            throw new NetworkNotAvailableException();
        }
        Request.Builder requestBuilder = chain.request().newBuilder();
        return chain.proceed(requestBuilder.build());
    }
}
