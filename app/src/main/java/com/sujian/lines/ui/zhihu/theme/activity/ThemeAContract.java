package com.sujian.lines.ui.zhihu.theme.activity;

import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;
import com.sujian.lines.data.entity.ThemeChildListBean;

import rx.Observable;

/**
 * Created by sujian on 2016/9/19.
 * Mail:121116111@qq.com
 */
public class ThemeAContract {

    interface Model extends BaseModel{
        Observable<ThemeChildListBean> getThemeChild(int id);
    }


    interface View extends BaseView{
        void showContent(ThemeChildListBean themeChildListBean);
    }

    static abstract class  Presenter extends BasePresenter<Model,View>{
         abstract void showThemeChild(int id);
    }
}
