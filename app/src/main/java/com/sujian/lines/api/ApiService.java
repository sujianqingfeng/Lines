package com.sujian.lines.api;

import com.sujian.lines.data.CreatedResult;
import com.sujian.lines.data.entity._User;


import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/3/23.
 */
public interface ApiService {
    @POST("users")
    Observable<CreatedResult> createUser(@Body _User user);

    @GET("login")
    Observable<_User> login(@Query("username") String username, @Query("password") String password);
}
