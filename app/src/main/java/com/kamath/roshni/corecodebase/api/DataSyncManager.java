package com.kamath.roshni.corecodebase.api;

import android.content.Context;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamath.roshni.corecodebase.BuildConfig;
import com.kamath.roshni.corecodebase.Config;
import com.kamath.roshni.corecodebase.constants.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by mac on 12/10/16.
 */

public class DataSyncManager {

    private static Retrofit retrofitHandle;

    public static ApplicationAPI getApplicationAPI(@NonNull Context context) {
        if (null == retrofitHandle) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder builder = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json");
                    return chain.proceed(builder.build());
                }
            });
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient.addInterceptor(logging);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);


            retrofitHandle = new Retrofit.Builder().baseUrl(Config.BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(httpClient.build())
                    .build();
        }


        ApplicationAPI api = retrofitHandle.create(ApplicationAPI.class);
        return api;
    }

    protected static void sendOnStartEvent(Handler handler, final DataSyncListener syncListener, final int requestCode) {
        if (handler != null && syncListener != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    syncListener.onStart(requestCode);
                }
            });
        }
    }

    protected static void sendOnErrorEvent(Handler handler, final DataSyncListener syncListener, final Throwable throwable, final int requestCode) {
        if (handler != null && syncListener != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    syncListener.onError(throwable, requestCode);
                }
            });
        }
    }

    protected static <T> void sendOnSuccessEvent(Handler handler, final DataSyncListener<T> syncListener, final T responseObject, final int requestCode) {
        if (handler != null && syncListener != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    syncListener.onSuccess(responseObject, requestCode);
                }
            });
        }
    }
}
