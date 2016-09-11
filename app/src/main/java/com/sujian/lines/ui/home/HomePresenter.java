package com.sujian.lines.ui.home;

/**
 * Created by sujian on 2016/9/8.
 * Mail:121116111@qq.com
 */
public class HomePresenter extends HomeContract.Presenter {

    @Override
    public void onStart() {
        showTabs();
    }

    @Override
    void showTabs() {
        mView.showTabs(mModel.getTabs());
    }
}
