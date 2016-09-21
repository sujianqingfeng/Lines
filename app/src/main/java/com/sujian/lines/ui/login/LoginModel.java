package com.sujian.lines.ui.login;



import com.sujian.lines.api.Api;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.entity._User;

import retrofit2.Call;
import rx.Observable;

/**
 * Created by sujian on 2016/9/3.
 * Mail:121116111@qq.com
 */
public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<_User> login(String userName, String userPassword) {

        return Api.getInstance()
                .getLeanCloundApiService()
                .login(userName,userPassword)
                .compose(RxSchedulers.io_main());

    }
}
