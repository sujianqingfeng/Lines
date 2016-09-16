package com.sujian.lines.ui.zhihu.detail;

import com.sujian.lines.api.ZhiHuApi;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.entity.DetailExtraBean;
import com.sujian.lines.data.entity.ZhihuDetailBean;

import rx.Observable;

/**
 * Created by sujian on 2016/9/15.
 * Mail:121116111@qq.com
 */
public class ZhihuDetailModel implements ZhihuDetailContract.Model {
    @Override
    public Observable<ZhihuDetailBean> getDetailData(int id) {
        return ZhiHuApi.getInstance()
                .service
                .getDetailInfo(id)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<DetailExtraBean> getExtraData(int id) {
        return ZhiHuApi.getInstance()
                .service
                .getDetailExtraInfo(id)
                .compose(RxSchedulers.io_main());
    }
}
