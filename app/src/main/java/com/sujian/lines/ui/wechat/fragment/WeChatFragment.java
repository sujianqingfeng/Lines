package com.sujian.lines.ui.wechat.fragment;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseFragment;
import com.sujian.lines.data.entity.WXItemBean;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class WeChatFragment extends BaseFragment<WeChatFPresenter,WeChatFModel> implements WeChatFContract.View {
    @Bind(R.id.rv_wechat_list)
    RecyclerView rvWechatList;
    @Bind(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    WechatAdapter mAdapter;
    List<WXItemBean> mList;

    boolean isLoadingMore = false;

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mAdapter = new WechatAdapter(mContext,mList);
        rvWechatList.setLayoutManager(new LinearLayoutManager(mContext));
        rvWechatList.setAdapter(mAdapter);
        rvWechatList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) rvWechatList.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = rvWechatList.getLayoutManager().getItemCount();
                if (lastVisibleItem >= totalItemCount - 2 && dy > 0) {  //还剩2个Item时加载更多
                    if(!isLoadingMore){
                        isLoadingMore = true;
                        mPresenter.getMoreWechatData();
                    }
                }
            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getWechatData();
            }
        });
        viewLoading.smoothToShow();
        mPresenter.getWechatData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_we_chat;
    }

    @Override
    public void showWeChat(List<WXItemBean> list) {
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
    public void showMoreChat(List<WXItemBean> list) {
        viewLoading.smoothToHide();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
        isLoadingMore = false;
    }
}
