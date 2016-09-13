package com.sujian.lines.ui.zhihu.theme;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.sujian.lines.R;
import com.sujian.lines.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * 主题
 */
public class ThemeFragment extends BaseFragment<ThemePresenter,ThemeModel> implements ThemeContract.View{

    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_theme;
    }
}
