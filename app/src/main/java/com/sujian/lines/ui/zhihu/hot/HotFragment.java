package com.sujian.lines.ui.zhihu.hot;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseFragment;
import com.sujian.lines.data.entity.HotListBean;
import com.sujian.lines.ui.zhihu.detail.ZhihuDetailActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 热门
 */
public class HotFragment extends BaseFragment<HotPresenter,HotModel> implements HotContract.View{

    @Bind(R.id.rv_hot_content)
    RecyclerView rvHotContent;
    @Bind(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    List<HotListBean.RecentBean> mList;
    HotAdapter mAdapter;
    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mAdapter = new HotAdapter(mContext,mList);
        rvHotContent.setVisibility(View.INVISIBLE);
        rvHotContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvHotContent.setAdapter(mAdapter);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.showHot();
            }
        });
        mAdapter.setOnItemClickListener(new HotAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View shareView) {
                mAdapter.setReadState(position,true);
                mAdapter.notifyItemChanged(position);
                Intent intent = new Intent();
                intent.setClass(mContext, ZhihuDetailActivity.class);
                intent.putExtra("id",mList.get(position).getNews_id());
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), shareView, "shareView");
                mContext.startActivity(intent,options.toBundle());
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    public void showHot(HotListBean hotListBean) {
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            viewLoading.smoothToHide();
        }
        rvHotContent.setVisibility(View.VISIBLE);
        mList.clear();
        mList.addAll(hotListBean.getRecent());
        mAdapter.notifyDataSetChanged();
    }
}
