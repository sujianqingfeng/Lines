package com.sujian.lines.ui.news;

import com.sujian.lines.api.Api;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.Pointer;
import com.sujian.lines.data.entity.Comment;

import rx.Observable;

/**
 * Created by sujian on 2016/9/11.
 * Mail:121116111@qq.com
 */
public class NewDetailedModel implements NewDetailedContract.Model {
    @Override
    public Observable createComment(String content, String nid, Pointer user) {
        return Api.getInstance()
                .service
                .createComment(new Comment(nid,content,user))
                .compose(RxSchedulers.io_main());
    }
}
