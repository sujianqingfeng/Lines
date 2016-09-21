package com.sujian.lines.ui.zhihu.theme.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.data.entity.ThemeChildListBean;
import com.sujian.lines.ui.zhihu.detail.ZhihuDetailActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static com.sujian.lines.ui.zhihu.theme.activity.ThemeAContract.*;

public class ThemeActivity extends BaseActivity<ThemeAPresenter,ThemeAModel> implements ThemeAContract.View {

    @Bind(R.id.rv_theme_child_list)
    RecyclerView rvThemeChildList;
    @Bind(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @Bind(R.id.tool_bar)
    Toolbar mToolBar;

    ThemeChildAdapter mAdapter;
    List<ThemeChildListBean.StoriesBean> mList;

    int id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_theme;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        id = intent.getExtras().getInt("id");
        viewLoading.smoothToShow();
        mList = new ArrayList<>();
        mAdapter = new ThemeChildAdapter(mContext, mList);
        rvThemeChildList.setLayoutManager(new LinearLayoutManager(mContext));
        rvThemeChildList.setAdapter(mAdapter);
        mPresenter.showThemeChild(id);
        mAdapter.setOnItemClickListener(new ThemeChildAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, android.view.View shareView) {

                mAdapter.setReadState(position, true);
                mAdapter.notifyItemChanged(position);
                Intent intent = new Intent();
                intent.setClass(mContext, ZhihuDetailActivity.class);
                intent.putExtra("id", mList.get(position).getId());
                if (shareView != null) {
                    mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mContext, shareView, "shareView").toBundle());
                } else {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mContext).toBundle());
                }
            }
        });
        rvThemeChildList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                android.view.View firstVisibleItem = recyclerView.getChildAt(0);
                int firstItemPosition = ((LinearLayoutManager) rvThemeChildList.getLayoutManager()).findFirstVisibleItemPosition();
                int itemHeight = firstVisibleItem.getHeight();
                int firstItemBottom = rvThemeChildList.getLayoutManager().getDecoratedBottom(firstVisibleItem);
                mAdapter.setTopAlpha(((firstItemPosition + 1) * itemHeight - firstItemBottom) * 2.0 / recyclerView.getChildAt(0).getHeight());
            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.showThemeChild(id);
            }
        });
    }

    @Override
    public void showContent(ThemeChildListBean themeChildListBean) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            viewLoading.smoothToHide();
        }
        setToolBar(mToolBar,themeChildListBean.getName());
        mList.clear();
        mList.addAll(themeChildListBean.getStories());
        mAdapter.notifyDataSetChanged();
        mAdapter.setTopInfo(themeChildListBean.getBackground(), themeChildListBean.getDescription());
    }
}
