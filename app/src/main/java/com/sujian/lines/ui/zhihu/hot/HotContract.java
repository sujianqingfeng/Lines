package com.sujian.lines.ui.zhihu.hot;

import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;
import com.sujian.lines.data.entity.HotListBean;

import rx.Observable;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public abstract class HotContract {

    interface Model extends BaseModel{
        Observable<HotListBean> getHot();
    }

    interface View extends BaseView{
        void showHot(HotListBean hotListBean);
    }

    static abstract class Presenter extends BasePresenter<Model,View>{
       abstract void showHot();
        @Override
        public abstract  void onStart();
    }
}
