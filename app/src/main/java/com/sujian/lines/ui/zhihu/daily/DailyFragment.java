package com.sujian.lines.ui.zhihu.daily;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseFragment;
import com.sujian.lines.data.entity.DailyBeforeListBean;
import com.sujian.lines.data.entity.DailyListBean;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 日报
 */
public class DailyFragment extends BaseFragment<DailyPresenter,DailyModel> implements DailyContract.View {

    @Bind(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @Bind(R.id.rv_daily_list)
    RecyclerView rvDailyList;
    DailyAdapter mAdapter;
    List<DailyListBean.StoriesBean> mList;

    String currentDate;


    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mAdapter = new DailyAdapter(mContext,mList);

        rvDailyList.setLayoutManager(new LinearLayoutManager(mContext));
        rvDailyList.setAdapter(mAdapter);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_daily;
    }

    @Override
    public void showDaily(DailyListBean info) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            viewLoading.smoothToHide();
        }
        mList = info.getStories();
        currentDate = String.valueOf(Integer.valueOf(info.getDate()) + 1);
        mAdapter.addDailyDate(info);
    }

    @Override
    public void showBeforeDaily(String date, DailyBeforeListBean info) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            viewLoading.smoothToHide();
        }

        mList = info.getStories();
        currentDate = String.valueOf(Integer.valueOf(info.getDate()));
        viewLoading.smoothToHide();
        mAdapter.addDailyBeforeDate(info);
    }

    @Override
    public void showLoading() {
        viewLoading.smoothToShow();
    }

}
