package com.example.a3899.bankqueue.service;

import com.example.a3899.bankqueue.entity.RegisterEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by a3899 on 2017/12/13.
 */

public interface RegisterService {
    @POST("register")
    @FormUrlEncoded
    Observable<RegisterEntity> register(@Field("username") String username, @Field("password") String password);
}
