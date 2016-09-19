package com.sujian.lines.ui.gank.tech;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseFragment;
import com.sujian.lines.base.util.DateUtil;
import com.sujian.lines.data.entity.GankItemBean;
import com.sujian.lines.ui.wechat.detail.TechDetailActivity;
import com.sujian.lines.ui.wechat.detail.TechDetailContract;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by sujian on 2016/9/19.
 * Mail:121116111@qq.com
 */
public class TechFragment extends BaseFragment<TechPresenter,TechModel> implements TechContract.View{


    @Bind(R.id.rv_tech_content)
    RecyclerView rvTechContent;
    @Bind(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    List<GankItemBean> mList;
    TechAdapter mAdapter;

    boolean isLoadingMore = false;
    String tech;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tech;
    }


    @Override
    protected void initView() {
        mPresenter.getGirlImage();
        mList = new ArrayList<>();
        tech = getArguments().getString("tech");
        mAdapter = new TechAdapter(mContext,mList,tech);
        rvTechContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvTechContent.setAdapter(mAdapter);
        viewLoading.smoothToShow();
        mPresenter.getGankData(tech);
        mAdapter.setOnItemClickListener(new TechAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View shareView) {
                Intent intent = new Intent();
                intent.setClass(mContext, TechDetailActivity.class);
                intent.putExtra("url",mList.get(position).getUrl());
                intent.putExtra("title",mList.get(position).getDesc());
                intent.putExtra("id",mList.get(position).get_id());
                intent.putExtra("tech",tech);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), shareView, "shareView");
                mContext.startActivity(intent,options.toBundle());
            }
        });
        rvTechContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) rvTechContent.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = rvTechContent.getLayoutManager().getItemCount();
                if (lastVisibleItem >= totalItemCount - 2 && dy > 0) {  //还剩2个Item时加载更多
                    if(!isLoadingMore){
                        isLoadingMore = true;
                        mPresenter.getMoreGankData(tech);
                    }
                }
                View firstVisibleItem = recyclerView.getChildAt(0);
                int firstItemPosition = ((LinearLayoutManager) rvTechContent.getLayoutManager()).findFirstVisibleItemPosition();
                int itemHeight = firstVisibleItem.getHeight();
                int firstItemBottom = rvTechContent.getLayoutManager().getDecoratedBottom(firstVisibleItem);
                mAdapter.setTopAlpha(((firstItemPosition + 1) * itemHeight - firstItemBottom) * 2.0 / recyclerView.getChildAt(0).getHeight());
            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getGankData(tech);
            }
        });
    }


    @Override
    public void showGank(List<GankItemBean> list) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            viewLoading.smoothToHide();
        }
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreGank(List<GankItemBean> list) {
        viewLoading.smoothToHide();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
        isLoadingMore = false;
    }

    @Override
    public void showGirlImage(List<GankItemBean> list) {
        mAdapter.setTopInfo(list.get(0).getUrl(), DateUtil.getCurrentDateString());
        mAdapter.notifyItemChanged(0);
    }
}
