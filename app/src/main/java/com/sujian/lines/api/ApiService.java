package com.sujian.lines.api;

import com.sujian.lines.data.CreatedResult;
import com.sujian.lines.data.Data;
import com.sujian.lines.data.entity.Comment;
import com.sujian.lines.data.entity.CommentInfo;
import com.sujian.lines.data.entity.Homeitem;
import com.sujian.lines.data.entity.HomeitemInfo;
import com.sujian.lines.data.entity._User;
import com.sujian.lines.ui.user.UserModel;


import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
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

    @GET("109-35?maxResult=20&needContent=1&showapi_appid=24251&showapi_sign=14f6b2f44ca6481e89b2d8b6153a3c4a")
    Observable<HomeitemInfo> getHomeItemInfo(@Query("channelName") String channelName, @Query("page") int page);

    @POST("classes/Comment")
    Observable<CreatedResult> createComment(@Body Comment mComment);

    @GET("classes/Comment")
    Observable<Data<CommentInfo>> getCommentList(@Query("include") String include, @Query("where") String where, @Query("skip") int skip, @Query("limit") int limit);

    @Headers("Content-Type: image/png")
    @POST("files/{name}")
    Observable<CreatedResult> upFile(@Path("name") String name, @Body RequestBody body);


    @PUT("users/{uid}")
    Observable<CreatedResult> upUser(@Header("X-LC-Session") String session, @Path("uid") String uid, @Body UserModel.Face face);


}
