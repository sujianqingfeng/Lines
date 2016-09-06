package com.sujian.lines.ui.register;


import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;
import com.sujian.lines.data.CreatedResult;

import rx.Observable;

/**
 * 注册契约类
 * Created by sujian on 2016/9/3.
 * Mail:121116111@qq.com
 */
public interface RegisterContract {

    interface Model extends BaseModel {
        Observable<CreatedResult> sign(String userName, String userPassword);
    }

    interface View extends BaseView {
        void signSuccess();
        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model,View> {

        abstract void sign(String userName, String userPassword);

        @Override
        public void onStart() {

        }
    }
}
