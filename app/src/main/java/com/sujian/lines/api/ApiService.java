package com.sujian.lines.api;

import com.sujian.lines.data.CreatedResult;
import com.sujian.lines.data.entity.CommentBean;
import com.sujian.lines.data.entity.DailyBeforeListBean;
import com.sujian.lines.data.entity.DailyListBean;
import com.sujian.lines.data.entity.DetailExtraBean;
import com.sujian.lines.data.entity.GankHttpResponse;
import com.sujian.lines.data.entity.GankItemBean;
import com.sujian.lines.data.entity.GankSearchItemBean;
import com.sujian.lines.data.entity.HotListBean;
import com.sujian.lines.data.entity.SectionChildListBean;
import com.sujian.lines.data.entity.SectionListBean;
import com.sujian.lines.data.entity.ThemeChildListBean;
import com.sujian.lines.data.entity.ThemeListBean;
import com.sujian.lines.data.entity.WXHttpResponse;
import com.sujian.lines.data.entity.WXItemBean;
import com.sujian.lines.data.entity.ZhihuDetailBean;
import com.sujian.lines.data.entity._User;
import com.sujian.lines.ui.user.UserModel;

import java.util.List;

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



    @Headers("Content-Type: image/png")
    @POST("files/{name}")
    Observable<CreatedResult> upFile(@Path("name") String name, @Body RequestBody body);


    @PUT("users/{uid}")
    Observable<CreatedResult> upUser(@Header("X-LC-Session") String session, @Path("uid") String uid, @Body UserModel.Face face);




    //知乎
    /**
     * 最新日报
     */
    @GET("news/latest")
    Observable<DailyListBean> getDailyList();

    /**
     * 往期日报
     */
    @GET("news/before/{date}")
    Observable<DailyBeforeListBean> getDailyBeforeList(@Path("date") String date);

    /**
     * 主题日报
     */
    @GET("themes")
    Observable<ThemeListBean> getThemeList();

    /**
     * 主题日报详情
     */
    @GET("theme/{id}")
    Observable<ThemeChildListBean> getThemeChildList(@Path("id") int id);

    /**
     * 专栏日报
     */
    @GET("sections")
    Observable<SectionListBean> getSectionList();

    /**
     * 专栏日报详情
     */
    @GET("section/{id}")
    Observable<SectionChildListBean> getSectionChildList(@Path("id") int id);

    /**
     * 热门日报
     */
    @GET("news/hot")
    Observable<HotListBean> getHotList();

    /**
     * 日报详情
     */
    @GET("news/{id}")
    Observable<ZhihuDetailBean> getDetailInfo(@Path("id") int id);

    /**
     * 日报的额外信息
     */
    @GET("story-extra/{id}")
    Observable<DetailExtraBean> getDetailExtraInfo(@Path("id") int id);

    /**
     * 日报的长评论
     */
    @GET("story/{id}/long-comments")
    Observable<CommentBean> getLongCommentInfo(@Path("id") int id);

    /**
     * 日报的短评论
     */
    @GET("story/{id}/short-comments")
    Observable<CommentBean> getShortCommentInfo(@Path("id") int id);


    /**
     * 微信精选列表
     */
    @GET("wxhot")
    Observable<WXHttpResponse<List<WXItemBean>>> getWXHot(@Query("num") int num, @Query("page") int page);

    /**
     * 微信精选列表
     */
    @GET("wxhot")
    Observable<WXHttpResponse<List<WXItemBean>>> getWXHotSearch(@Query("num") int num, @Query("page") int page, @Query("word") String word);


    /**
     * 技术文章列表
     */
    @GET("data/{tech}/{num}/{page}")
    Observable<GankHttpResponse<List<GankItemBean>>> getTechList(@Path("tech") String tech, @Path("num") int num, @Path("page") int page);

    /**
     * 妹纸列表
     */
    @GET("data/福利/{num}/{page}")
    Observable<GankHttpResponse<List<GankItemBean>>> getGirlList(@Path("num") int num, @Path("page") int page);

    /**
     * 随机妹纸图
     */
    @GET("random/data/福利/{num}")
    Observable<GankHttpResponse<List<GankItemBean>>> getRandomGirl(@Path("num") int num);

    /**
     * 搜索
     */
    @GET("search/query/{query}/category/{type}/count/{count}/page/{page}")
    Observable<GankHttpResponse<List<GankSearchItemBean>>> getSearchList(@Path("query") String query, @Path("type") String type, @Path("count") int num, @Path("page") int page);


}
