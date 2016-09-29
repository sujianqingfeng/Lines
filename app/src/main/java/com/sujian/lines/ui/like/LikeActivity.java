package com.sujian.lines.ui.like;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.base.util.ToastUtil;
import com.sujian.lines.data.Data;
import com.sujian.lines.data.entity.Like;
import com.sujian.lines.view.layout.SpaceItemDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class LikeActivity extends BaseActivity<LikePresenter, LikeModel> implements LikeContract.View {

    @Bind(R.id.tb_like)
    Toolbar tb_like;
    @Bind(R.id.rv_like)
    SwipeMenuRecyclerView rv_like;
    @Bind(R.id.srl_like)
    SwipeRefreshLayout srl_like;

    private List<Like> data;
    private LikeAdapter likeAdapter;
    private boolean isLoadMore;

    @Override
    public int getLayoutId() {
        return R.layout.activity_like;
    }

    @Override
    public void initView() {
        setToolBar(tb_like, "收藏");
        mPresenter.getLikeData();

        //侧滑菜单
        SwipeMenuCreator swipeMenuCreator = (swipeLeftMenu, swipeRightMenu, viewType) -> {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
                    .setBackgroundDrawable(R.drawable.selector_gray)
                    .setImage(R.mipmap.ic_delete) // 图标。
                    .setText("删除") // 文字。
                    .setTextColor(Color.WHITE) // 文字颜色。
                    .setTextSize(16) // 文字大小。
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);
        };

        data = new ArrayList<>();
        likeAdapter = new LikeAdapter(mContext, data);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_like.setLayoutManager(linearLayoutManager);


        rv_like.setAdapter(likeAdapter);
        rv_like.setSwipeMenuCreator(swipeMenuCreator);
        rv_like.addItemDecoration(new SpaceItemDecoration(12));

        //刷新事件
        srl_like.setOnRefreshListener(() -> {
            mPresenter.getLikeData();
            isLoadMore = false;
        });

        //监听滑动事件  自动添加更多
        rv_like.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int titalCount = recyclerView.getAdapter().getItemCount();
                if (lastItem >= titalCount - 2 && dy > 0) {//滑动到最后两个加载数据
                    mPresenter.getLikeData();
                    isLoadMore = true;
                    srl_like.setRefreshing(true);
                }
            }
        });


        //侧滑菜单的点击事件
        rv_like.setSwipeMenuItemClickListener((closeable, adapterPosition, menuPosition, direction) -> {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。
            Like remove = data.remove(adapterPosition);
            mPresenter.deleteLike(remove);
            likeAdapter.notifyItemRemoved(adapterPosition);
        });

    }

    @Override
    public void showLike(Data<Like> data) {
        if (isLoadMore)
            this.data.addAll(data.results);
        else
            this.data.addAll(0, data.results);
        srl_like.setRefreshing(false);
        likeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.show(msg);
    }
}
