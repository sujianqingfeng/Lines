package com.sujian.lines.ui.zhihu.theme;

import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;
import com.sujian.lines.data.entity.ThemeListBean;

import rx.Observable;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public abstract class ThemeContract {

    interface Model extends BaseModel{
        Observable<ThemeListBean> getTheme();
     }

    interface View extends BaseView{
        void showTheme(ThemeListBean themeListBean);
    }

    static abstract class Presenter extends BasePresenter<Model,View>{

        abstract void getTheme();

        @Override
        public abstract void onStart();
    }
}
