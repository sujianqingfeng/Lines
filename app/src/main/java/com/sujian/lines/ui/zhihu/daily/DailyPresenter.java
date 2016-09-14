package com.sujian.lines.ui.zhihu.daily;

import com.orhanobut.logger.Logger;
import com.sujian.lines.C;
import com.sujian.lines.data.entity.DailyBeforeListBean;
import com.sujian.lines.data.entity.DailyListBean;
import com.sujian.lines.data.event.TimeEvent;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class DailyPresenter extends DailyContract.Presenter {
    @Override
    void showDaily() {

        mRxManager.add(mModel.getDailyData()
                .subscribe(new Subscriber<DailyListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DailyListBean dailyListBean) {
                        Logger.e("日报数据--"+dailyListBean.toString());
                        mView.showDaily(dailyListBean);
                    }
                })
        );


    }

    @Override
    public void onStart() {
        showDaily();
        mRxManager.on(C.EVENT_TIME, new Action1<Object>() {
            @Override
            public void call(Object object) {
                TimeEvent time=(TimeEvent) object;
                Logger.e("接收到homeactivity的事件"+time.toString());
                mView.showLoading();
                mRxManager.add(mModel.getBeforeDailyData(time)
                .subscribe(new Subscriber<DailyBeforeListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DailyBeforeListBean dailyBeforeListBean) {
                        int year = Integer.valueOf(dailyBeforeListBean.getDate().substring(0,4));
                        int month = Integer.valueOf(dailyBeforeListBean.getDate().substring(4,6));
                        int day = Integer.valueOf(dailyBeforeListBean.getDate().substring(6,8));
                        mView.showBeforeDaily(String.format("%d年%d月%d日",year,month,day),dailyBeforeListBean);
                    }
                })
                );
            }
        });
    }
}
