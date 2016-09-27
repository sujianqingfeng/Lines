package com.sujian.lines.ui.like;

import com.sujian.lines.api.Api;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.Data;
import com.sujian.lines.data.entity.Like;

import rx.Observable;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class LikeModel implements LikeContract.Model {
    @Override
    public Observable<Data<Like>> getLikeData(String where,int limit) {
        return Api.getInstance()
                .getLeanCloundApiService()
                .queryLike(where,limit)
                .compose(RxSchedulers.io_main());
    }
}
