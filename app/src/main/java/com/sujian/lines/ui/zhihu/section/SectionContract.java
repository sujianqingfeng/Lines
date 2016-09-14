package com.sujian.lines.ui.zhihu.section;

import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;
import com.sujian.lines.data.entity.SectionListBean;

import rx.Observable;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public abstract class SectionContract {

    interface Model extends BaseModel{
        Observable<SectionListBean> getSectionList();
    }

    interface View extends BaseView{
         void showSection(SectionListBean sectionListBean);
    }

    static abstract class Presenter extends BasePresenter<Model,View>{
        abstract void getSection();
        @Override
        public abstract void onStart();
    }
}
