package com.sujian.lines.ui.login;



import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;
import com.sujian.lines.data.entity._User;

import retrofit2.Call;
import rx.Observable;

/**
 * 登录契约类
 * Created by sujian on 2016/9/3.
 * Mail:121116111@qq.com
 */
public interface LoginContract  {
    interface Model extends BaseModel {
        Observable<_User> login(String userName, String userPassword);
    }

    interface View extends BaseView {
        void loginSuccess();
        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model,View> {
        abstract void login(String userName, String userPassword);

        @Override
        public void onStart() {

        }
    }
}
