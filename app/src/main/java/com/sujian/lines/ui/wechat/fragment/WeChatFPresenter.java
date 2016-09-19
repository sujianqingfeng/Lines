package com.sujian.lines.ui.wechat.fragment;

import com.sujian.lines.C;
import com.sujian.lines.data.entity.WXItemBean;

import java.util.List;

import rx.Subscriber;

/**
 * Created by sujian on 2016/9/19.
 * Mail:121116111@qq.com
 */
public class WeChatFPresenter extends WeChatFContract.Presenter {
    public static final String TECH_WECHAT = "微信";
    private static final int NUM_OF_PAGE = 20;

    private int currentPage = 1;
    private String queryStr = null;
    @Override
    public void onStart() {

    }

    @Override
    void getWechatData() {
        currentPage = 1;
        mRxManager.add(mModel.getWechatData(NUM_OF_PAGE,currentPage)
        .subscribe(new Subscriber<List<WXItemBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<WXItemBean> wxItemBeen) {
                mView.showWeChat(wxItemBeen);
            }
        })
        );
    }

    @Override
    void getMoreWechatData() {
        mRxManager.add(mModel.getWechatData(NUM_OF_PAGE,++currentPage)
                .subscribe(new Subscriber<List<WXItemBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<WXItemBean> wxItemBeen) {
                        mView.showMoreChat(wxItemBeen);
                    }
                })
        );
    }
}
