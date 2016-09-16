package com.sujian.lines.ui.zhihu.comment.fragment;

import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.data.entity.CommentBean;

import rx.Subscriber;

/**
 * Created by sujian on 2016/9/16.
 * Mail:121116111@qq.com
 */
public class CommentFPresenter extends CommentFContract.Presenter {

    @Override
    public void onStart() {

    }

    @Override
    void getCommentData(int id, int commentKind) {
        mRxManager.add(mModel.getCommentData(id,commentKind)
        .subscribe(new Subscriber<CommentBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CommentBean commentBean) {
                mView.showContent(commentBean);
            }
        })
        );
    }
}
