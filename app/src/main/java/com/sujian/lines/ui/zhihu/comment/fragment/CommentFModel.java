package com.sujian.lines.ui.zhihu.comment.fragment;

import com.sujian.lines.api.Api;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.entity.CommentBean;

import rx.Observable;

/**
 * Created by sujian on 2016/9/16.
 * Mail:121116111@qq.com
 */
public class CommentFModel implements CommentFContract.Model {

    public static final int SHORT_COMMENT = 0;

    public static final int LONG_COMMENT = 1;
    @Override
    public Observable<CommentBean> getCommentData(int id, int commentKind) {
        if(commentKind == SHORT_COMMENT) {
            return Api.getInstance()
                    .getZhihuApiService()
                    .getShortCommentInfo(id)
                    .compose(RxSchedulers.io_main());
        } else {
            return Api.getInstance()
                    .getZhihuApiService()
                    .getLongCommentInfo(id)
                    .compose(RxSchedulers.io_main());
        }
    }
}
