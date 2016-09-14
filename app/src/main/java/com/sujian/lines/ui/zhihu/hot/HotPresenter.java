package com.sujian.lines.ui.zhihu.hot;

import com.sujian.lines.data.entity.HotListBean;

import rx.Subscriber;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class HotPresenter extends HotContract.Presenter {
    @Override
    void showHot() {
        mRxManager.add(mModel.getHot()
        .subscribe(new Subscriber<HotListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HotListBean hotListBean) {
                mView.showHot(hotListBean);
            }
        })
        );
    }

    @Override
    public void onStart() {
        showHot();
    }
}
