package com.sujian.lines.ui.zhihu.daily;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.sujian.lines.R;
import com.sujian.lines.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * 日报
 */
public class DailyFragment extends BaseFragment<DailyPresenter,DailyModel> implements DailyContract.View {


    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_daily;
    }
}
