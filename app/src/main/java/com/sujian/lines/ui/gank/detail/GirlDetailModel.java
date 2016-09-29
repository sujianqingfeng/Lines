package com.sujian.lines.ui.gank.detail;

import com.sujian.lines.api.Api;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.CreatedResult;
import com.sujian.lines.data.Data;
import com.sujian.lines.data.entity.Like;

import rx.Observable;

/**
 * 这个GirlDetailModel和TechDetailModel是一样的 可以直接弄成一个  我为了结构清晰 就不合并了
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class GirlDetailModel implements GirlDetailContract.Model {
    @Override
    public Observable<CreatedResult> inserLike(Like like) {
        return Api.getInstance()
                .getLeanCloundApiService()
                .createLike(like)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<Data<Like>> qureyLike(String where, int limit) {
        return Api.getInstance()
                .getLeanCloundApiService()
                .queryLike(where,limit)
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
