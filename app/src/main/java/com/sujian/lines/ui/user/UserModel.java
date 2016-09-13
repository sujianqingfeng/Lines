package com.sujian.lines.ui.user;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.sujian.lines.api.Api;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.CreatedResult;
import com.sujian.lines.data.entity._User;
import com.sujian.lines.rxkit.GlideBlurOnSubscribe;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by sujian on 2016/9/12.
 * Mail:121116111@qq.com
 */
public class UserModel implements UserContract.Model {

    public class Face {
        String face;

        public Face(String f) {
            this.face = f;
        }
    }

    @Override
    public Observable<CreatedResult> upFile(File file) {
        return Api.getInstance()
                .service
                .upFile(file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable upUser(_User user) {
        return Api.getInstance().service
                .upUser(user.getSessionToken(), user.objectId, new Face(user.getFace()))
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<GlideDrawable> blurBG(Context context, String url) {
         return Observable.create(new GlideBlurOnSubscribe(context, 100, url))
                .compose(RxSchedulers.<GlideDrawable>io_main());
    }

}
