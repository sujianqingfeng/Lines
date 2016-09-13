package com.sujian.lines.ui.user;

import android.content.Context;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;
import com.sujian.lines.data.CreatedResult;
import com.sujian.lines.data.entity._User;

import java.io.File;

import rx.Observable;

/**
 * 用户信息契约类
 * Created by sujian on 2016/9/12.
 * Mail:121116111@qq.com
 */
public class UserContract {
    interface Model extends BaseModel{
        Observable<CreatedResult> upFile(File file);

        Observable upUser( _User user);

        Observable<GlideDrawable> blurBG(Context context, String url);
    }


    interface View extends BaseView{
        void showMsg(String msg);
        void  initUser(_User user);
        void shouBG(GlideDrawable glideDrawable);
    }

    abstract static class Presenter extends BasePresenter<Model,View>{
        public abstract void upLoadFace(File f);

        public abstract void blurBG(Context context, String url);

        public abstract void upUserInfo(String face);
        @Override
        public void onStart() {

        }
    }
}
