package com.sujian.lines.ui.zhihu.detail;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.sujian.lines.C;
import com.sujian.lines.base.util.ApiUtil;
import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.data.CreatedResult;
import com.sujian.lines.data.Data;
import com.sujian.lines.data.Pointer;
import com.sujian.lines.data.entity.DetailExtraBean;
import com.sujian.lines.data.entity.Like;
import com.sujian.lines.data.entity.LikeQuery;
import com.sujian.lines.data.entity.ZhihuDetailBean;

import rx.Subscriber;

/**
 * Created by sujian on 2016/9/15.
 * Mail:121116111@qq.com
 */
public class ZhihuDetailPresenter extends ZhihuDetailContract.Presenter {

    private ZhihuDetailBean mData;
    private String objectId;

    @Override
    public void onStart() {
    }

    @Override
    void getDetailData(int id) {
        mRxManager.add(mModel.getDetailData(id)
        .subscribe(new Subscriber<ZhihuDetailBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.shouMsg("获取信息失败");
            }

            @Override
            public void onNext(ZhihuDetailBean zhihuDetailBean) {
                mData=zhihuDetailBean;
                mView.showDetailData(zhihuDetailBean);
            }
        })
        );
    }

    @Override
    void getExtraData(int id) {
        mRxManager.add(mModel.getExtraData(id)
        .subscribe(new Subscriber<DetailExtraBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DetailExtraBean detailExtraBean) {
                mView.showExtraData(detailExtraBean);
            }
        })
        );
    }

    @Override
    void insertLikeData() {
        if (mData==null)
            return;
        Logger.e(mData.getId()+"");
        Like like=new Like();
        like.setLid(String.valueOf(mData.getId()));
        like.setCreate(ApiUtil.getPointer(SpUtil.getUser()));
        like.setImage(mData.getImage());
        like.setTitle(mData.getTitle());
        like.setType(C.TYPE_ZHIHU);
        like.setTime(System.currentTimeMillis());
        mRxManager.add(mModel.inserLike(like)
        .subscribe(new Subscriber<CreatedResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CreatedResult createdResult) {
                mView.shouMsg("收藏成功");
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
                mView.shouMsg("取消收藏失败");
            }

            @Override
            public void onNext(CreatedResult createdResult) {
                mView.shouMsg("取消收藏");
            }
        })
        );
    }

    @Override
    void queryLikeData(int id) {

        LikeQuery likeQuery=new LikeQuery();
        likeQuery.setCreate(ApiUtil.getPointer(SpUtil.getUser()));
        likeQuery.setLid(String.valueOf(id));

        mRxManager.add(mModel.qureyLike(new Gson().toJson(likeQuery),10)
        .subscribe(new Subscriber<Data<Like>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Data<Like> likeData) {
                if (likeData.results.size()!=0){
                    objectId=likeData.results.get(0).objectId;
                    mView.setLikeCheck(true);
                }
            }
        })
        );
    }
}
