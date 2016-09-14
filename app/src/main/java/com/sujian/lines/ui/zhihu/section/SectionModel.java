package com.sujian.lines.ui.zhihu.section;

import com.sujian.lines.api.ZhiHuApi;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.entity.SectionListBean;

import rx.Observable;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class SectionModel implements SectionContract.Model {
    @Override
    public Observable<SectionListBean> getSectionList() {
        return ZhiHuApi.getInstance()
                .service.getSectionList()
                .compose(RxSchedulers.io_main());
    }
}
