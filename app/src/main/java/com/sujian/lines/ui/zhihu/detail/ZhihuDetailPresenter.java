package com.sujian.lines.ui.zhihu.detail;

import com.orhanobut.logger.Logger;
import com.sujian.lines.data.entity.DetailExtraBean;
import com.sujian.lines.data.entity.ZhihuDetailBean;

import rx.Subscriber;

/**
 * Created by sujian on 2016/9/15.
 * Mail:121116111@qq.com
 */
public class ZhihuDetailPresenter extends ZhihuDetailContract.Presenter {
    @Override
    public void onStart() {
    }

    @Override
    void getDetailData(int id) {

        Logger.e("model 是否为空" +(mModel==null));

        mRxManager.add(mModel.getDetailData(id)
        .subscribe(new Subscriber<ZhihuDetailBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ZhihuDetailBean zhihuDetailBean) {
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
    void insertLikeData(int id) {

    }

    @Override
    void deleteLikeData(int id) {

    }

    @Override
    void queryLikeData(int id) {

    }
}
