package com.sujian.lines.ui.zhihu.daily;

import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class DailyContract {

    interface Model extends BaseModel{}

    interface View extends BaseView{}

    static class Presenter extends BasePresenter<Model,View>{

        @Override
        public void onStart() {

        }
    }
}
