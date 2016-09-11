package com.sujian.lines.ui.news;


import com.sujian.lines.base.util.ApiUtil;
import com.sujian.lines.data.entity.Homeitem;
import com.sujian.lines.data.entity._User;

import rx.Subscriber;

/**
 * Created by sujian on 2016/9/11.
 * Mail:121116111@qq.com
 */
public class NewDetailedPresenter extends NewDetailedContract.Presenter {
    @Override
    public void createComment(String content,  String nid, _User user) {
        mRxManager.add(
                mModel.createComment(content, nid, ApiUtil.getPointer(user))
                        .subscribe(new Subscriber() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                mView.commentFail();
                            }

                            @Override
                            public void onNext(Object o) {
                                mView.commentSuc();
                            }
                        })
        );
    }
}
