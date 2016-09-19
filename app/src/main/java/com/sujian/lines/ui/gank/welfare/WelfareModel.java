package com.sujian.lines.ui.gank.welfare;

import com.sujian.lines.api.GankApi;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.entity.GankItemBean;

import java.util.List;

import rx.Observable;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class WelfareModel implements WelfareContract.Model {
    @Override
    public Observable<List<GankItemBean>> getGirlList(int num, int page) {
        return GankApi.getInstance()
                .service
                .getGirlList(num, page)
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.handleResult());
    }
}
