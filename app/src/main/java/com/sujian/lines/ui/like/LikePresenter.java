package com.sujian.lines.ui.like;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.sujian.lines.base.util.ApiUtil;
import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.data.Data;
import com.sujian.lines.data.entity.Like;
import com.sujian.lines.data.entity.LikeQuery;

import rx.Subscriber;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class LikePresenter extends LikeContract.Presenter {
    @Override
    void getLikeData() {
        LikeQuery likeQuery=new LikeQuery();
        likeQuery.setCreate(ApiUtil.getPointer(SpUtil.getUser()));
        mRxManager.add(mModel.getLikeData(new Gson().toJson(likeQuery),10)
        .subscribe(new Subscriber<Data<Like>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Data<Like> likeData) {
                mView.showLike(likeData);
            }
        })
        );
    }


    @Override
    public void onStart() {
    }
}
