package com.sujian.lines.ui.zhihu.theme;

import com.orhanobut.logger.Logger;
import com.sujian.lines.base.RxManager;
import com.sujian.lines.data.entity.ThemeListBean;

import rx.Subscriber;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class ThemePresenter extends ThemeContract.Presenter {
    @Override
    void getTheme() {
        mRxManager.add(mModel.getTheme()
                .subscribe(new Subscriber<ThemeListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ThemeListBean themeListBean) {
                        Logger.e("主题数据--"+themeListBean.toString());
                        mView.showTheme(themeListBean);
                    }
                }));
    }

    @Override
    public void onStart() {
        getTheme();
    }
}
