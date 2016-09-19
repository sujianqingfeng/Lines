package com.sujian.lines.ui.gank.tech;

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
public class TechContract {

    interface Model extends BaseModel {
        Observable<List<GankItemBean>> getGankData(String tech, int num, int page);
        Observable<List<GankItemBean>> getRandomGirl(int num);
    }

    interface View extends BaseView {
        void showGank(List<GankItemBean> list);
        void showMoreGank(List<GankItemBean> list);
        void showGirlImage(List<GankItemBean> list);
    }

    static abstract class Presenter extends BasePresenter<Model, View> {

        abstract void getGankData(String tech);

        abstract void getMoreGankData(String tech);

        abstract void getGirlImage();

        @Override
        public abstract void onStart();
    }
}
