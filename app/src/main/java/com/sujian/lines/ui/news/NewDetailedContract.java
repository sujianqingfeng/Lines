package com.sujian.lines.ui.news;

import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;
import com.sujian.lines.data.Pointer;
import com.sujian.lines.data.entity.Homeitem;
import com.sujian.lines.data.entity._User;

import rx.Observable;

/**
 * 新闻详情契约类
 * Created by sujian on 2016/9/11.
 * Mail:121116111@qq.com
 */
public class NewDetailedContract {

    interface Model extends BaseModel{
        Observable createComment(String content, String nid, Pointer user);
    }

    interface View extends BaseView{
        void commentSuc();
        void commentFail();
        void showLoginAction();
    }

    static abstract class Presenter extends BasePresenter<Model,View>{
        public abstract void createComment(String content, String nid, _User user);
        @Override
        public void onStart() {

        }
    }
}
