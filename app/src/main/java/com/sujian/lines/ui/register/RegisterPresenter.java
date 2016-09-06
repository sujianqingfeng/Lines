package com.sujian.lines.ui.register;

import com.sujian.lines.data.CreatedResult;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by sujian on 2016/9/6.
 * Mail:121116111@qq.com
 */
public class RegisterPresenter extends RegisterContract.Presenter {
    @Override
    void sign(String userName, String userPassword) {
        mRxManager.add(mModel.sign(userName,userPassword)
        .subscribe(new Subscriber<CreatedResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showMsg("注册失败");
            }

            @Override
            public void onNext(CreatedResult createdResult) {
                mView.showMsg("注册成功");
                mView.signSuccess();
            }
        }));
    }
}
