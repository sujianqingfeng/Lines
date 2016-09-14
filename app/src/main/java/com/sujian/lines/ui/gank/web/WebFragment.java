package com.sujian.lines.ui.gank.web;


import com.sujian.lines.R;
import com.sujian.lines.base.BaseFragment;
import com.sujian.lines.view.layout.LoadingPage;


/**
 * 热门
 */
public class WebFragment extends BaseFragment<WebPresenter,WebModel> implements WebContract.View{


    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_web;
    }
}
