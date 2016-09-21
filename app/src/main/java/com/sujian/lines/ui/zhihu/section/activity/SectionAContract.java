package com.sujian.lines.ui.zhihu.section.activity;

import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;
import com.sujian.lines.data.entity.SectionChildListBean;
import com.sujian.lines.data.entity.SectionListBean;

import rx.Observable;

/**
 * Created by sujian on 2016/9/19.
 * Mail:121116111@qq.com
 */
public class SectionAContract {

    interface Model extends BaseModel{
        Observable<SectionChildListBean> getSectionData(int id);
    }

    interface View extends BaseView{
        void showSection(SectionChildListBean sectionListBean);
    }

    static abstract class Presenter extends BasePresenter<Model,View>{
        abstract void  getSectionData(int id);
    }
}
