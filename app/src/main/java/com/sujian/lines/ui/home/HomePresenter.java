package com.sujian.lines.ui.home;

import android.util.Log;

import com.sujian.lines.C;
import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.data.entity._User;

/**
 * Created by sujian on 2016/9/8.
 * Mail:121116111@qq.com
 */
public class HomePresenter extends HomeContract.Presenter {

    @Override
    public void onStart() {
        showTabs();
        getUserInfo();
        mRxManager.on(C.EVENT_LOGIN, arg -> mView.initUserInfo((_User) arg));
    }

    @Override
    void showTabs() {
        mView.showTabs(mModel.getTabs());
    }

    @Override
    public void getUserInfo() {
        _User user = SpUtil.getUser();
        System.err.println(user==null);
        if (user != null)
            mView.initUserInfo(user);
    }
}
