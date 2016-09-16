package com.sujian.lines.ui.zhihu.comment.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseFragment;
import com.sujian.lines.data.entity.CommentBean;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by sujian on 2016/9/16.
 * Mail:121116111@qq.com
 */
public class CommentFragment extends BaseFragment<CommentFPresenter,CommentFModel> implements CommentFContract.View{
    @Bind(R.id.rv_comment_list)
    RecyclerView rvCommentList;
    @Bind(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;

    CommentAdapter mAdapter;
    List<CommentBean.CommentsBean> mList;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        mPresenter.getCommentData(bundle.getInt("id"),bundle.getInt("kind"));
        viewLoading.smoothToShow();
        rvCommentList.setVisibility(View.INVISIBLE);
        mList = new ArrayList<>();
        mAdapter = new CommentAdapter(mContext,mList);
        rvCommentList.setLayoutManager(new LinearLayoutManager(mContext));
        rvCommentList.setAdapter(mAdapter);
    }

    @Override
    public void showContent(CommentBean commentBean) {
        viewLoading.smoothToHide();
        rvCommentList.setVisibility(View.VISIBLE);
        mList.clear();
        mList.addAll(commentBean.getComments());
        mAdapter.notifyDataSetChanged();
    }
}
