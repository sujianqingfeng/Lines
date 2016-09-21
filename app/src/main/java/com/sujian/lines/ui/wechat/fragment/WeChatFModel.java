package com.sujian.lines.ui.wechat.fragment;

import com.sujian.lines.api.Api;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.entity.WXItemBean;

import java.util.List;

import rx.Observable;

/**
 * Created by sujian on 2016/9/19.
 * Mail:121116111@qq.com
 */
public class WeChatFModel implements WeChatFContract.Model {
    @Override
    public Observable<List<WXItemBean>> getWechatData(int num, int page) {
        return Api.getInstance()
                .getWeChatApiService()
                .getWXHot(num,page)
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.handleWXResult());
    }


}
