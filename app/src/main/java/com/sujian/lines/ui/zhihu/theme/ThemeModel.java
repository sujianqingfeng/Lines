package com.sujian.lines.ui.zhihu.theme;

import com.sujian.lines.api.ZhiHuApi;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.entity.ThemeListBean;

import rx.Observable;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class ThemeModel implements ThemeContract.Model {
    @Override
    public Observable<ThemeListBean> getTheme() {
        return ZhiHuApi.getInstance()
                .service
                .getThemeList()
                .compose(RxSchedulers.io_main());
    }
}
