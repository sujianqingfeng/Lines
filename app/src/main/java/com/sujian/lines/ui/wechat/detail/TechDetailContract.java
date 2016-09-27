package com.sujian.lines.ui.wechat.detail;

import android.content.Intent;

import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;
import com.sujian.lines.data.CreatedResult;
import com.sujian.lines.data.Data;
import com.sujian.lines.data.entity.Like;

import rx.Observable;

/**
 * 微信详情契约类
 * Created by sujian on 2016/9/19.
 * Mail:121116111@qq.com
 */
public class TechDetailContract {

    interface Model extends BaseModel{
        Observable<CreatedResult> inserLike(Like like);
        Observable<Data<Like>> qureyLike(String where, int limit);
        Observable<CreatedResult> deleteLike(String objectId);
    }

    interface View extends BaseView{
        void setLikeState(boolean isLike);
        void showMsg(String msg);
    }

    static abstract class Presenter extends BasePresenter<Model,View>{
        abstract  void  insertLikeData(Intent intent);
        abstract  void  deleteLikeData();
        abstract  void  queryLikeData(String id);
    }
}
