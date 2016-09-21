package com.sujian.lines.ui.gank.tech;

import com.sujian.lines.api.Api;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.entity.GankItemBean;

import java.util.List;

import rx.Observable;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class TechModel implements TechContract.Model {
    @Override
    public Observable<List<GankItemBean>> getGankData(String tech, int num, int page) {
        return Api.getInstance()
                .getGankApiService()
                .getTechList(tech,num,page)
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.handleResult());
    }

    @Override
    public Observable<List<GankItemBean>> getRandomGirl(int num) {
        return Api.getInstance()
                .getGankApiService()
                .getRandomGirl(num)
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.handleResult());
    }
}
