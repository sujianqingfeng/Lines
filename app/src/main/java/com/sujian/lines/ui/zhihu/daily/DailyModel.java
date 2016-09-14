package com.sujian.lines.ui.zhihu.daily;

import com.sujian.lines.api.ZhiHuApi;
import com.sujian.lines.base.util.DateUtil;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.entity.DailyBeforeListBean;
import com.sujian.lines.data.entity.DailyListBean;
import com.sujian.lines.data.event.TimeEvent;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class DailyModel implements DailyContract.Model {
    @Override
    public Observable<DailyListBean> getDailyData() {
        return ZhiHuApi.getInstance()
                .service
                .getDailyList()
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<DailyBeforeListBean> getBeforeDailyData(TimeEvent timeEvent) {
       return Observable.just(timeEvent)
                .subscribeOn(Schedulers.io())
                .map(new Func1<TimeEvent, String>() {
                    @Override
                    public String call(TimeEvent timeEvent) {
                        StringBuilder date = new StringBuilder();
                        String year = String.valueOf(timeEvent.getYear());
                        String month = String.valueOf(timeEvent.getMonth());
                        String day = String.valueOf(timeEvent.getDay()+1);
                        if(month.length() < 2) {
                            month = "0" + month;
                        }
                        if(day.length() < 2) {
                            day = "0" + day;
                        }
                        return date.append(year).append(month).append(day).toString();
                    }
                })
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        if (s.equals(DateUtil.getCurrentDate()))
                            return false;
                        return true;
                    }
                })
                .flatMap(new Func1<String, Observable<DailyBeforeListBean>>() {
                    @Override
                    public Observable<DailyBeforeListBean> call(String s) {
                        return ZhiHuApi.getInstance()
                                .service
                                .getDailyBeforeList(s);
                    }
                })
                .compose(RxSchedulers.io_main());
    }
}
