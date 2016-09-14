package com.sujian.lines.ui.zhihu.section;

import android.support.v4.content.pm.ActivityInfoCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseFragment;
import com.sujian.lines.data.entity.SectionListBean;
import com.sujian.lines.view.layout.LoadingPage;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 专栏
 */
public class SectionFragment extends BaseFragment<SectionPresenter,SectionModel> implements SectionContract.View {
    @Bind(R.id.rv_section_list)
    RecyclerView rvSectionList;
    @Bind(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    List<SectionListBean.DataBean> mList;
    SectionAdapter mAdapter;

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mAdapter = new SectionAdapter(mContext,mList);
        rvSectionList.setLayoutManager(new GridLayoutManager(mContext, 2));
        rvSectionList.setAdapter(mAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_section;
    }

    @Override
    public void showSection(SectionListBean sectionListBean) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
            viewLoading.smoothToHide();
        }
        mList.clear();
        mList.addAll(sectionListBean.getData());
        mAdapter.notifyDataSetChanged();
    }
}
