package com.sujian.lines.ui.login;

import android.util.Log;

import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.data.entity._User;

import rx.Subscriber;

/**
 * Created by sujian on 2016/9/3.
 * Mail:121116111@qq.com
 */
public class LoginPresenter extends LoginContract.Presenter {
    @Override
    void login(String userName, String userPassword) {

        mRxManager.add(mModel.login(userName,userPassword)
                .subscribe(new Subscriber<_User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showMsg("登录失败");
                    }

                    @Override
                    public void onNext(_User user) {
                        mView.loginSuccess();
                        SpUtil.setUser(user);
                        mView.showMsg("登录成功");
                    }
                }));



    }
}
