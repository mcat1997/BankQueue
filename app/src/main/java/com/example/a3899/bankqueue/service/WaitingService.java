package com.example.a3899.bankqueue.service;

import com.example.a3899.bankqueue.entity.WaitingEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by a3899 on 2017/12/14.
 */

public interface WaitingService {
    @GET("business/info")
    Observable<WaitingEntity> waiting(@Query("userId") Integer userId);
}
