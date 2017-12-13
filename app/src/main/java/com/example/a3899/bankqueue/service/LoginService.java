package com.example.a3899.bankqueue.service;

import com.example.a3899.bankqueue.entity.LoginEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {

    @POST("login")
    @FormUrlEncoded
    Observable<LoginEntity> login(@Field("username") String username, @Field("password") String password);

}
