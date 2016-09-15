package com.sujian.lines.ui.zhihu.activity;

import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;
import com.sujian.lines.data.entity.DetailExtraBean;
import com.sujian.lines.data.entity.ZhihuDetailBean;

import rx.Observable;

/**
 * 知乎详细页面的契约类
 * Created by sujian on 2016/9/15.
 * Mail:121116111@qq.com
 */
public class ZhihuDetailContract {
    interface Model extends BaseModel{
        Observable<ZhihuDetailBean> getDetailData(int id);
        Observable<DetailExtraBean> getExtraData(int id);
    }

    interface View extends BaseView{
        void showDetailData(ZhihuDetailBean zhihuDetailBean);
        void showExtraData(DetailExtraBean detailExtraBean);
    }


    static abstract class  Presenter extends BasePresenter<Model,View>{
        abstract  void  getDetailData(int id);
        abstract  void  getExtraData(int id);
        abstract  void  insertLikeData(int id);
        abstract  void  deleteLikeData(int id);
        abstract  void  queryLikeData(int id);

    }
}
