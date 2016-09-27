package com.sujian.lines.ui.like;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.data.Data;
import com.sujian.lines.data.entity.Like;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class LikeActivity extends BaseActivity<LikePresenter,LikeModel> implements LikeContract.View {

    @Bind(R.id.tb_like)
    Toolbar tb_like;
    @Bind(R.id.rv_like)
    SwipeMenuRecyclerView rv_like;
    private List<Like> data;
    private LikeAdapter likeAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_like;
    }

    @Override
    public void initView() {
        setToolBar(tb_like,"收藏");
        mPresenter.getLikeData();

        //侧滑菜单
        SwipeMenuCreator swipeMenuCreator=new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_gray)
                        .setImage(R.mipmap.ic_delete) // 图标。
                        .setText("删除") // 文字。
                        .setTextColor(Color.WHITE) // 文字颜色。
                        .setTextSize(16) // 文字大小。
                        .setWidth(20)
                        .setHeight(20);
                swipeRightMenu.addMenuItem(deleteItem);
            }
        };

        data=new ArrayList<>();
        likeAdapter=new LikeAdapter(mContext,data);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_like.setLayoutManager(linearLayoutManager);


        rv_like.setAdapter(likeAdapter);
        rv_like.setSwipeMenuCreator(swipeMenuCreator);


    }

    @Override
    public void showLike(Data<Like> data) {
        this.data.addAll(data.results);
        likeAdapter.notifyDataSetChanged();
    }
}
