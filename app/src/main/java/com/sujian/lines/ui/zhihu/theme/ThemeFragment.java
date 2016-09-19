package com.sujian.lines.ui.zhihu.theme;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.sujian.lines.R;
import com.sujian.lines.base.BaseFragment;
import com.sujian.lines.data.entity.ThemeListBean;
import com.sujian.lines.ui.zhihu.theme.activity.ThemeActivity;
import com.sujian.lines.view.layout.LoadingPage;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 主题
 */
public class ThemeFragment extends BaseFragment<ThemePresenter,ThemeModel> implements ThemeContract.View{
    @Bind(R.id.rv_theme_list)
    RecyclerView rvThemeList;
    @Bind(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    ThemeAdapter mAdapter;
    List<ThemeListBean.OthersBean> mList = new ArrayList<>();

    @Override
    protected void initView() {
        mAdapter = new ThemeAdapter(mContext, mList);
        rvThemeList.setLayoutManager(new GridLayoutManager(mContext, 2));
        rvThemeList.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ThemeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent();
                intent.setClass(mContext, ThemeActivity.class);
                intent.putExtra("id", id);
                mContext.startActivity(intent);
            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getTheme();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_theme;
    }

    @Override
    public void showTheme(ThemeListBean themeListBean) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            viewLoading.smoothToHide();
        }
        mList.clear();
        mList.addAll(themeListBean.getOthers());
        mAdapter.notifyDataSetChanged();
    }
}
