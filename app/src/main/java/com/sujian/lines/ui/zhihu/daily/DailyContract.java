package com.sujian.lines.ui.zhihu.daily;

import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;
import com.sujian.lines.data.entity.DailyBeforeListBean;
import com.sujian.lines.data.entity.DailyListBean;
import com.sujian.lines.data.event.TimeEvent;

import rx.Observable;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class DailyContract {

    interface Model extends BaseModel{
        Observable<DailyListBean> getDailyData();
        Observable<DailyBeforeListBean> getBeforeDailyData(TimeEvent timeEvent);
    }

    interface View extends BaseView{
        void showDaily(DailyListBean info);
        void showBeforeDaily(String date,DailyBeforeListBean info);
        void showLoading();
    }

    static abstract class Presenter extends BasePresenter<Model,View>{
        abstract void showDaily();
        @Override
        public abstract void onStart();
    }
}
