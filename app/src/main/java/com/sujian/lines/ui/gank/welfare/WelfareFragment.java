package com.sujian.lines.ui.gank.welfare;


import com.sujian.lines.R;
import com.sujian.lines.base.BaseFragment;
import com.sujian.lines.view.layout.LoadingPage;


/**
 * 热门
 */
public class WelfareFragment extends BaseFragment<WelfarePresenter,WelfareModel> implements WelfareContract.View{


    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_welfare;
    }
}
