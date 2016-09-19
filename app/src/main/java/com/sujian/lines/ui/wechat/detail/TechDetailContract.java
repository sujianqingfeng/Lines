package com.sujian.lines.ui.wechat.detail;

import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;

/**
 * 微信详情契约类
 * Created by sujian on 2016/9/19.
 * Mail:121116111@qq.com
 */
public class TechDetailContract {

    interface Model extends BaseModel{

    }

    interface View extends BaseView{

    }

    static abstract class Presenter extends BasePresenter<Model,View>{


    }
}
