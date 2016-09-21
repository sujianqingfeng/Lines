package com.sujian.lines.api;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sujian.lines.App;
import com.sujian.lines.C;
import com.sujian.lines.base.util.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 获取apiservice
 */
public class Api {

    public static final int DEFAULT_TIMEOUT = 5;

    //拦截日志
    private  HttpLoggingInterceptor interceptor;
    //缓存
    private  Cache cache;

    private  Gson gson;


    //构造方法私有
    private Api() {
        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(App.getAppContext().getCacheDir(), "cache");
        cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();

    }


    public  ApiService getLeanCloundApiService() {
        String BASE_URL = "https://leancloud.cn:443/1.1/";
        String X_LC_Id = "t6AeDTpmn32eDWInYGbafqoi-gzGzoHsz";
        String X_LC_Key = "eKOuGRhDME8tx4QiCjDyN1tM";
        Interceptor mInterceptor = (chain) -> chain.proceed(chain.request().newBuilder()
                .addHeader("X-LC-Id", X_LC_Id)
                .addHeader("X-LC-Key", X_LC_Key)
                .addHeader("Content-Type", "application/json")
                .build());


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(7676, TimeUnit.MILLISECONDS)
                .connectTimeout(7676, TimeUnit.MILLISECONDS)
                .addInterceptor(mInterceptor)
                .addInterceptor(interceptor)
                .addInterceptor(getInterceptor())
                .addNetworkInterceptor(getNetWorkInterceptor())
                .cache(cache)
                .build();


        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiService.class);

    }

    public  ApiService getZhihuApiService() {
        String BASE_URL = "http://news-at.zhihu.com/api/4/";
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(7676, TimeUnit.MILLISECONDS)
                .connectTimeout(7676, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(getInterceptor())
                .addNetworkInterceptor(getNetWorkInterceptor())
                .cache(cache)
                .build();


        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiService.class);
    }

    public  ApiService getWeChatApiService() {
        String BASE_URL = "http://apis.baidu.com/txapi/weixin/";
        Interceptor mInterceptor = (chain) -> chain.proceed(chain.request().newBuilder()
                .addHeader("apikey", C.BAIDU_KEY)
                .addHeader("Content-Type", "application/json")
                .build());

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(7676, TimeUnit.MILLISECONDS)
                .connectTimeout(7676, TimeUnit.MILLISECONDS)
                .addInterceptor(mInterceptor)
                .addInterceptor(interceptor)
                .addInterceptor(getInterceptor())
                .addNetworkInterceptor(getNetWorkInterceptor())
                .cache(cache)
                .build();


        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiService.class);
    }

    public  ApiService getGankApiService() {
        String BASE_URL = "http://gank.io/api/";
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(7676, TimeUnit.MILLISECONDS)
                .connectTimeout(7676, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(getInterceptor())
                .addNetworkInterceptor(getNetWorkInterceptor())
                .cache(cache)
                .build();


        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiService.class);
    }


    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();
    }

    //获取单例
    public static Api getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 设置返回数据的  Interceptor  判断网络   没网读取缓存
     */
    public Interceptor getInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetWorkUtil.isNetConnected(App.getAppContext())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                return chain.proceed(request);
            }
        };
    }


    /**
     * 设置连接器  设置缓存
     */
    public Interceptor getNetWorkInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                if (NetWorkUtil.isNetConnected(App.getAppContext())) {
                    int maxAge = 0 * 60;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为1周
                    int maxStale = 60 * 60 * 24 * 7;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
    }


}