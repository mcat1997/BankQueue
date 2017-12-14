package com.example.a3899.bankqueue.service;


import com.example.a3899.bankqueue.entity.BookEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by a3899 on 2017/12/14.
 */

public interface BookService {
    @POST("business/add")
    @FormUrlEncoded
    Observable<BookEntity> book(@Field("userId") Integer userId);
}
