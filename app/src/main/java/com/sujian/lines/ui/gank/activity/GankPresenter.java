package com.sujian.lines.ui.gank.activity;

import com.sujian.lines.C;
import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.data.entity._User;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class GankPresenter extends GankContract.Presenter {
    @Override
    public void onStart() {
        mRxManager.on(C.EVENT_LOGIN, arg -> mView.initUserInfo((_User) arg));
    }

    @Override
    void getUserInfo() {
        _User user = SpUtil.getUser();
        if (user != null)
            mView.initUserInfo(user);
    }
}
