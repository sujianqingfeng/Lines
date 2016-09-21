package com.sujian.lines.ui.zhihu.section.activity;

import com.sujian.lines.api.Api;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.entity.SectionChildListBean;

import rx.Observable;

/**
 * Created by sujian on 2016/9/19.
 * Mail:121116111@qq.com
 */
public class SectionAModel implements SectionAContract.Model {
    @Override
    public Observable<SectionChildListBean> getSectionData(int id) {
        return Api.getInstance()
                .getZhihuApiService()
                .getSectionChildList(id)
                .compose(RxSchedulers.io_main());
    }
}
