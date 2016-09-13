package com.sujian.lines.ui.user;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.sujian.lines.C;
import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.data.entity._User;

import java.io.File;

import rx.Subscriber;

/**
 * Created by sujian on 2016/9/12.
 * Mail:121116111@qq.com
 */
public class UserPresenter extends UserContract.Presenter {
    @Override
    public void upLoadFace(File file) {
        mRxManager.add(mModel.upFile(file).subscribe(
                res -> upUserInfo(res.getUrl()),
                e -> mView.showMsg("上传失败!")));

    }

    @Override
    public void blurBG(Context context, String url) {

        mRxManager.add(mModel.blurBG(context,url)
        .subscribe(new Subscriber<GlideDrawable>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GlideDrawable glideDrawable) {
                mView.shouBG(glideDrawable);
            }
        })
        );
    }

    @Override
    public void upUserInfo(String face) {
        _User user = SpUtil.getUser();
        user.setFace(face);
        mRxManager.add(mModel.upUser(user).subscribe(
                res -> {
                    SpUtil.setUser(user);
                    mRxManager.post(C.EVENT_LOGIN, user);
                    mView.showMsg("更新成功!");
                },
                e -> mView.showMsg("更新失败!")));
    }

    @Override
    public void onStart() {
        mRxManager.on(C.EVENT_LOGIN, user -> mView.initUser((_User) user));
    }
}
