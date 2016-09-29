package com.sujian.lines.ui.gank.detail;

import com.sujian.lines.data.CreatedResult;
import com.sujian.lines.data.Data;
import com.sujian.lines.data.entity.Like;

import rx.Observable;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class GirlDetailModel implements GirlDetailContract.Model {
    @Override
    public Observable<CreatedResult> inserLike(Like like) {
        return null;
    }

    @Override
    public Observable<Data<Like>> qureyLike(String where, int limit) {
        return null;
    }

    @Override
    public Observable<CreatedResult> deleteLike(String objectId) {
        return null;
    }
}
