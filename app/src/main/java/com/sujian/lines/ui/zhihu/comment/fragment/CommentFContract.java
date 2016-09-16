package com.sujian.lines.ui.zhihu.comment.fragment;

import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;
import com.sujian.lines.data.entity.CommentBean;

import rx.Observable;

/**
 * Created by sujian on 2016/9/16.
 * Mail:121116111@qq.com
 */
public class CommentFContract {

    interface Model extends BaseModel{
        Observable<CommentBean> getCommentData(int id, int commentKind);
    }

    interface View extends BaseView{
        void showContent(CommentBean commentBean);
    }

    static abstract class  Presenter extends BasePresenter<Model,View>{
       abstract void getCommentData(int id, int commentKind);

    }
}
