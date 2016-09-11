package com.sujian.lines.data.repository;

import com.sujian.lines.C;
import com.sujian.lines.api.Api;
import com.sujian.lines.base.util.ApiUtil;
import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.Data;
import com.sujian.lines.data.Repository;
import com.sujian.lines.data.entity.CommentInfo;

import rx.Observable;

/**
 * 评论数据
 * Created by sujian on 2016/9/11.
 * Mail:121116111@qq.com
 */
public class CommentInfoRepository extends Repository<CommentInfo>{
    @Override
    public Observable<Data<CommentInfo>> getPageAt(int page) {

        return Api.getInstance()
                .service
                .getCommentList(ApiUtil.getInclude(param),param.get("where"), C.PAGE_COUNT * (page - 1),C.PAGE_COUNT)
                .compose(RxSchedulers.io_main());
    }
}
