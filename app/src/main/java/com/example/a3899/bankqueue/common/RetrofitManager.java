package com.example.a3899.bankqueue.common;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static final String BASE_URL = "http://192.168.33.98:5000/android/";

    private static final Retrofit retrofit;

    static {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }

}
