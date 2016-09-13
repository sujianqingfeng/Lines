package com.sujian.lines.ui.gank.android;


import com.sujian.lines.R;
import com.sujian.lines.base.BaseFragment;


/**
 * android
 */
public class AndroidFragment extends BaseFragment<AdnroidPresenter,AndroidModel> implements AndroidContract.View{

    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_android;
    }
}
