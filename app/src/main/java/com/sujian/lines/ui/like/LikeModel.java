package com.sujian.lines.ui.like;

import com.sujian.lines.api.Api;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.CreatedResult;
import com.sujian.lines.data.Data;
import com.sujian.lines.data.entity.Like;

import rx.Observable;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class LikeModel implements LikeContract.Model {
    @Override
    public Observable<Data<Like>> getLikeData(String where,int skip,int limit) {
        return Api.getInstance()
                .getLeanCloundApiService()
                .queryLike(where,skip,limit)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<CreatedResult> deleteLike(String where) {
        return Api.getInstance()
                .getLeanCloundApiService()
                .deleteLike(where)
                .compose(RxSchedulers.io_main());
    }
}
