package com.sujian.lines.ui.home;

import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;
import com.sujian.lines.ui.register.RegisterContract;

/**
 * Created by sujian on 2016/9/8.
 * Mail:121116111@qq.com
 */
public class HomeContract {
    interface Model extends BaseModel{
        String[] getTabs();
    }

    interface View extends BaseView{
       void showTabs(String[] tabs);
    }

    abstract static class Presenter extends BasePresenter<Model,View>{
        abstract void showTabs();
    }
}
