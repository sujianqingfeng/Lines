package com.sujian.lines.ui.gank.welfare;

import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;
import com.sujian.lines.data.entity.GankItemBean;

import java.util.List;

import rx.Observable;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class WelfareContract {

    interface Model extends BaseModel{
        Observable<List<GankItemBean>> getGirlList(int num, int page);
    }

    interface View extends BaseView{
        void showGirl(List<GankItemBean> list);
        void showMoreGirl(List<GankItemBean> list);
    }

    static abstract class Presenter extends BasePresenter<Model,View>{

        abstract void getGirlData();

        abstract void getMoreGirlData();
        @Override
        public abstract void  onStart();
    }
}
