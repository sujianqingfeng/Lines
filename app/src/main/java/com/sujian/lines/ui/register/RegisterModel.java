package com.sujian.lines.ui.register;

import com.sujian.lines.api.Api;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.CreatedResult;
import com.sujian.lines.data.entity._User;

import rx.Observable;

/**
 * Created by sujian on 2016/9/6.
 * Mail:121116111@qq.com
 */
public class RegisterModel implements RegisterContract.Model {
    @Override
    public Observable<CreatedResult> sign(String userName, String userPassword) {
        return Api.getInstance()
                .service
                .createUser(new _User(userName,userPassword))
                .compose(RxSchedulers.io_main());
    }
}
