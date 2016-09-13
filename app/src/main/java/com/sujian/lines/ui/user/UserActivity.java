package com.sujian.lines.ui.user;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.sujian.lines.C;
import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.base.util.ImageUtil;
import com.sujian.lines.base.util.ToastUtil;
import com.sujian.lines.data.entity._User;
import com.sujian.lines.view.layout.TRecyclerView;
import com.sujian.lines.view.viewholder.UserItemVH;

import java.io.File;

import butterknife.Bind;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.BaseResultEvent;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;

public class UserActivity extends BaseActivity<UserPresenter, UserModel> implements UserContract.View {

    public static final String TRANSLATE_VIEW = "share_img";

    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.lv_user)
    TRecyclerView lv_user;
    @Bind(R.id.im_header)
    ImageView im_header;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    public void initView() {
        _User user = (_User) getIntent().getSerializableExtra(C.HEAD_DATA);
        initUser(user);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ViewCompat.setTransitionName(image, TRANSLATE_VIEW);

        lv_user.setHeadView(UserItemVH.class)
                .setIsRefreshable(false)
                .fetch();

        fab.setOnClickListener((view ->
                chooseDialog()
        ));
    }



    //头像选择对话框
    private void chooseDialog() {
        new MaterialDialog.Builder(this)
                .title(R.string.picturechoose)
                .items(R.array.items)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        gallery();
                    }
                })
                .show();
    }

    //图片选择器
    private void gallery() {
        RxGalleryFinal
                .with(mContext)
                .image()
                .radio()
                .crop()
                .imageLoader(ImageLoaderType.GLIDE)
                .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                    @Override
                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                        String cropPath = imageRadioResultEvent.getResult().getCropPath();
                        ImageUtil.loadRoundImg(image, cropPath);

                        File file = new File(cropPath);
                        mPresenter.upLoadFace(file);
                    }
                })
                .openGallery();
    }

    @Override
    public void initUser(_User user) {
        ImageUtil.loadRoundAndBgImg(image, user.getFace(), im_header);
        setTitle(user.getUsername());
    }

    @Override
    public void shouBG(GlideDrawable glideDrawable) {
        im_header.setImageDrawable(glideDrawable);
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.show(msg);
    }
}
