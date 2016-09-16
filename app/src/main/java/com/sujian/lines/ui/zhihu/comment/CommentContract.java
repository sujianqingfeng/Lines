package com.sujian.lines.ui.zhihu.comment;

import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;

/**
 * Created by sujian on 2016/9/16.
 * Mail:121116111@qq.com
 */
public class CommentContract {

    interface Model extends BaseModel{}

    interface View extends BaseView{}

    static abstract class  Presenter extends BasePresenter<Model,View>{


    }
}
