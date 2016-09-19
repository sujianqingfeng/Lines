package com.sujian.lines.ui.gank.welfare;

import com.sujian.lines.data.entity.GankItemBean;

import java.util.List;

import rx.Subscriber;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class WelfarePresenter extends WelfareContract.Presenter {

    public static final int NUM_OF_PAGE = 20;

    private int currentPage = 1;

    @Override
    void getGirlData() {
        currentPage = 1;
        mRxManager.add(mModel.getGirlList(NUM_OF_PAGE,currentPage)
        .subscribe(new Subscriber<List<GankItemBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<GankItemBean> list) {
                mView.showGirl(list);
            }
        })
        );
    }

    @Override
    void getMoreGirlData() {
        mRxManager.add(mModel.getGirlList(NUM_OF_PAGE,++currentPage)
                .subscribe(new Subscriber<List<GankItemBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<GankItemBean> list) {
                        mView.showGirl(list);
                    }
                })
        );
    }

    @Override
    public void onStart() {

    }
}
