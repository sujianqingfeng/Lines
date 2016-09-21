package com.sujian.lines.ui.zhihu.theme.activity;

import com.sujian.lines.data.entity.ThemeChildListBean;

import rx.Subscriber;

/**
 * Created by sujian on 2016/9/19.
 * Mail:121116111@qq.com
 */
public class ThemeAPresenter extends ThemeAContract.Presenter {
    @Override
    public void onStart() {

    }

    @Override
    void showThemeChild(int id) {
        mRxManager.add(mModel.getThemeChild(id)
        .subscribe(new Subscriber<ThemeChildListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ThemeChildListBean themeChildListBean) {
                mView.showContent(themeChildListBean);
            }
        })
        );
    }
}
