package com.sujian.lines.ui.gank.tech;

import com.sujian.lines.data.entity.GankItemBean;

import java.util.List;

import rx.Subscriber;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class TechPresenter extends TechContract.Presenter {
    public static final String TECH_ANDROID = "Android";
    public static final String TECH_IOS = "iOS";
    public static final String TECH_WEB = "前端";
    private static final int NUM_OF_PAGE = 20;

    private int currentPage = 1;
    private String queryStr = null;
    private String currentTech = TECH_ANDROID;

    @Override
    void getGankData(String tech) {
        queryStr = null;
        currentPage = 1;
        currentTech = tech;
        mRxManager.add(mModel.getGankData(tech,NUM_OF_PAGE,currentPage)
        .subscribe(new Subscriber<List<GankItemBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<GankItemBean> list) {
                mView.showGank(list);
            }
        })
        );
    }

    @Override
    void getMoreGankData(String tech) {
        if(queryStr != null) {
            getMoreSearchGankData();
            return;
        }

        mRxManager.add(mModel.getGankData(tech,NUM_OF_PAGE,++currentPage)
        .subscribe(new Subscriber<List<GankItemBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<GankItemBean> list) {
                mView.showMoreGank(list);
            }
        })
        );
    }

    @Override
    void getGirlImage() {
        mRxManager.add(mModel.getRandomGirl(1)
        .subscribe(new Subscriber<List<GankItemBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<GankItemBean> list) {
                mView.showGirlImage(list);
            }
        })
        );
    }

    private void getMoreSearchGankData() {
    }

    @Override
    public void onStart() {

    }
}
