package com.sujian.lines.ui.zhihu.section.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.data.entity.SectionChildListBean;
import com.sujian.lines.data.entity.SectionListBean;
import com.sujian.lines.ui.zhihu.detail.ZhihuDetailActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class SectionActivity extends BaseActivity<SectionAPresenter,SectionAModel> implements SectionAContract.View {

    @Bind(R.id.rv_section_content)
    RecyclerView rvSectionContent;
    @Bind(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @Bind(R.id.tool_bar)
    Toolbar mToolBar;

    List<SectionChildListBean.StoriesBean> mList;
    SectionChildAdapter mAdapter;

    int id;
    String title;
    @Override
    public int getLayoutId() {
        return R.layout.activity_section;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        title = intent.getStringExtra("title");
        setToolBar(mToolBar,title);
        mList = new ArrayList<>();
        mAdapter = new SectionChildAdapter(mContext, mList);
        rvSectionContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvSectionContent.setAdapter(mAdapter);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getSectionData(id);
            }
        });
        mAdapter.setOnItemClickListener(new SectionChildAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position, View shareView) {

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
        mPresenter.getSectionData(id);
        viewLoading.smoothToShow();
    }

    @Override
    public void showSection(SectionChildListBean sectionChildListBean) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            viewLoading.smoothToHide();
        }
        mList.clear();
        mList.addAll(sectionChildListBean.getStories());
        mAdapter.notifyDataSetChanged();
    }
}
