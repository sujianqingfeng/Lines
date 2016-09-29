package com.sujian.lines.ui.gank.detail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.base.util.ShareUtil;
import com.sujian.lines.base.util.SystemUtil;
import com.sujian.lines.base.util.ToastUtil;

import butterknife.Bind;
import uk.co.senab.photoview.PhotoViewAttacher;

public class GirlDetailActivity extends BaseActivity<GirlDetailPresenter,GirlDetailModel> implements GirlDetailContract.View {


    @Bind(R.id.tool_bar)
    Toolbar toolBar;
    @Bind(R.id.iv_girl_detail)
    ImageView ivGirlDetail;

    Bitmap bitmap;
    PhotoViewAttacher mAttacher;
    MenuItem menuItem;

    String url;
    String id;

    boolean isLiked;

    @Override
    public int getLayoutId() {
        return R.layout.activity_girl_detail;
    }

    @Override
    public void initView() {
        setToolBar(toolBar,"");
        Intent intent = getIntent();
        url = intent.getExtras().getString("url");
        id = intent.getExtras().getString("id");
        if (url != null) {
            Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    bitmap = resource;
                    ivGirlDetail.setImageBitmap(resource);
                    mAttacher = new PhotoViewAttacher(ivGirlDetail);
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.girl_menu,menu);
        menuItem = menu.findItem(R.id.action_like);
        mPresenter.queryLikeData(id);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_like:
                if(isLiked) {
                    //item.setIcon(R.mipmap.ic_toolbar_like_n);
                    mPresenter.deleteLikeData();
                } else {
                    //item.setIcon(R.mipmap.ic_toolbar_like_p);
                    mPresenter.insertLikeData(getIntent());
                }
                break;
            case R.id.action_save:
                SystemUtil.saveBitmapToFile(mContext,url,bitmap,ivGirlDetail,false);
                break;
            case R.id.action_share:
                ShareUtil.shareImage(mContext,SystemUtil.saveBitmapToFile(mContext,url,bitmap,ivGirlDetail,true),"分享一只妹纸");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setLikeState(boolean state) {
        if(state) {
            menuItem.setIcon(R.mipmap.ic_toolbar_like_p);
            isLiked = true;
        } else {
            menuItem.setIcon(R.mipmap.ic_toolbar_like_n);
            isLiked = false;
        }
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.show(msg);
    }
}
