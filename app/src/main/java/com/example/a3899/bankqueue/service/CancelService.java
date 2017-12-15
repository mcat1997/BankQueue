package com.example.a3899.bankqueue.service;

import com.example.a3899.bankqueue.entity.CancelEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by a3899 on 2017/12/15.
 */

public interface CancelService {
    @POST("business/cancel")
    @FormUrlEncoded
    Observable<CancelEntity> cancel(@Field("userId") Integer userId);
}
