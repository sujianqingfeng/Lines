package com.sujian.lines.ui.gank.activity;

import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;
import com.sujian.lines.data.entity._User;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class GankContract {

    interface Model extends BaseModel{}

    interface View extends BaseView{
        void initUserInfo(_User user);
    }

    static abstract class Presenter extends BasePresenter<Model,View>{
        @Override
        public abstract void onStart();
        abstract void getUserInfo();
    }
}
