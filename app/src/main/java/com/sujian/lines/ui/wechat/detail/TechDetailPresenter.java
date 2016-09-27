package com.sujian.lines.ui.wechat.detail;

import android.content.Intent;

import com.google.gson.Gson;
import com.sujian.lines.base.util.ApiUtil;
import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.data.CreatedResult;
import com.sujian.lines.data.Data;
import com.sujian.lines.data.entity.Like;
import com.sujian.lines.data.entity.LikeQuery;
import com.sujian.lines.ui.gank.tech.TechPresenter;

import rx.Subscriber;

/**
 * Created by sujian on 2016/9/19.
 * Mail:121116111@qq.com
 */
public class TechDetailPresenter extends TechDetailContract.Presenter {

    private String objectId;

    @Override
    public void onStart() {

    }


    @Override
    void insertLikeData(Intent intent) {

        String tech = intent.getExtras().getString("tech");
        String title = intent.getExtras().getString("title");
        String url = intent.getExtras().getString("url");
        String id = intent.getExtras().getString("id");
        Like like = new Like();
        like.setLid(id);
        like.setCreate(ApiUtil.getPointer(SpUtil.getUser()));
        like.setType(TechPresenter.getTechType(tech));
        like.setTitle(title);
        like.setImage(url);
        like.setTime(System.currentTimeMillis());
        mRxManager.add(mModel.inserLike(like)
                .subscribe(new Subscriber<CreatedResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showMsg("收藏失败");
                    }

                    @Override
                    public void onNext(CreatedResult createdResult) {
                        mView.showMsg("收藏成功");
                        mView.setLikeState(true);
                        objectId=createdResult.getObjectId();
                    }
                })
        );
    }

    @Override
    void deleteLikeData() {
        mRxManager.add(mModel.deleteLike(objectId)
        .subscribe(new Subscriber<CreatedResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showMsg("取消收藏失败");
            }

            @Override
            public void onNext(CreatedResult createdResult) {
                mView.showMsg("取消收藏");
                mView.setLikeState(false);
            }
        })
        );
    }

    @Override
    void queryLikeData(String id) {
        LikeQuery likeQuery = new LikeQuery();
        likeQuery.setLid(id);
        likeQuery.setCreate(ApiUtil.getPointer(SpUtil.getUser()));
        mRxManager.add(mModel.qureyLike(new Gson().toJson(likeQuery), 10)
                .subscribe(new Subscriber<Data<Like>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Data<Like> likeData) {
                        if (likeData.results.size() != 0){
                            mView.setLikeState(true);
                            objectId=likeData.results.get(0).objectId;
                        }

                    }
                })
        );
    }
}
