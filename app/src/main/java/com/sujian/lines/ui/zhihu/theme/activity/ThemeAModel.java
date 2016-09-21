package com.sujian.lines.ui.zhihu.theme.activity;

import com.sujian.lines.api.Api;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.entity.ThemeChildListBean;

import rx.Observable;

/**
 * Created by sujian on 2016/9/19.
 * Mail:121116111@qq.com
 */
public class ThemeAModel implements ThemeAContract.Model{
    @Override
    public Observable<ThemeChildListBean> getThemeChild(int id) {
        return Api.getInstance()
                .getZhihuApiService()
                .getThemeChildList(id)
                .compose(RxSchedulers.io_main());
    }
}
