package com.sujian.lines.ui.wechat.fragment;

import com.sujian.lines.base.BaseModel;
import com.sujian.lines.base.BasePresenter;
import com.sujian.lines.base.BaseView;
import com.sujian.lines.base.BaseViewPagerActivity;
import com.sujian.lines.data.entity.WXItemBean;

import java.util.List;

import rx.Observable;

/**
 * Created by sujian on 2016/9/19.
 * Mail:121116111@qq.com
 */
public class WeChatFContract {

    interface Model extends BaseModel{
        Observable<List<WXItemBean>> getWechatData(int num,int page);
    }

    interface View extends BaseView {
        void showWeChat(List<WXItemBean> list);
        void showMoreChat(List<WXItemBean> list);
    }

    static abstract class Presenter extends BasePresenter<Model,View>{
        abstract void getWechatData();
        abstract void getMoreWechatData();
    }
}
