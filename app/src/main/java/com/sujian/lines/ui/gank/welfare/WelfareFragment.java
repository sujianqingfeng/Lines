package com.sujian.lines.ui.gank.welfare;


import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseFragment;
import com.sujian.lines.data.entity.GankItemBean;
import com.sujian.lines.ui.gank.detail.GirlDetailActivity;
import com.sujian.lines.view.layout.LoadingPage;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 热门
 */
public class WelfareFragment extends BaseFragment<WelfarePresenter,WelfareModel> implements WelfareContract.View{
    @Bind(R.id.rv_girl_content)
    RecyclerView rvGirlContent;
    @Bind(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private static final int SPAN_COUNT = 2;

    GirlAdapter mAdapter;
    List<GankItemBean> mList;
    StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    private boolean isLoadingMore = false;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_welfare;
    }


    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mAdapter = new GirlAdapter(mContext, mList);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(SPAN_COUNT,StaggeredGridLayoutManager.VERTICAL);
        mStaggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rvGirlContent.setLayoutManager(mStaggeredGridLayoutManager);
        rvGirlContent.setAdapter(mAdapter);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getGirlData();
            }
        });
        rvGirlContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] visibleItems = mStaggeredGridLayoutManager.findLastVisibleItemPositions(null);
                int lastItem = Math.max(visibleItems[0],visibleItems[1]);
                if (lastItem > mAdapter.getItemCount() - 5 && !isLoadingMore && dy > 0 ) {
                    isLoadingMore = true;
                    mPresenter.getMoreGirlData();
                }
            }
        });
        mAdapter.setOnItemClickListener(new GirlAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View shareView) {
                Intent intent = new Intent();
                intent.setClass(mContext, GirlDetailActivity.class);
                intent.putExtra("url",mList.get(position).getUrl());
                intent.putExtra("id",mList.get(position).get_id());
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), shareView, "shareView");
                mContext.startActivity(intent,options.toBundle());
            }
        });
        viewLoading.smoothToShow();
        mPresenter.getGirlData();
    }


    @Override
    public void showGirl(List<GankItemBean> list) {
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            viewLoading.smoothToHide();
        }
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreGirl(List<GankItemBean> list) {
        isLoadingMore = false;
        viewLoading.smoothToHide();
        mList.addAll(list);
        for(int i =mList.size() - WelfarePresenter.NUM_OF_PAGE ; i < mList.size(); i++) {    //使用notifyDataSetChanged已加载的图片会有闪烁，遂使用inserted逐个插入
            mAdapter.notifyItemInserted(i);
        }
    }
}
