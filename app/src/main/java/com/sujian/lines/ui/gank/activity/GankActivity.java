package com.sujian.lines.ui.gank.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sujian.lines.C;
import com.sujian.lines.R;
import com.sujian.lines.base.BaseViewPagerActivity;
import com.sujian.lines.base.util.ActivityFragmentInject;
import com.sujian.lines.base.util.ImageUtil;
import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.data.entity._User;
import com.sujian.lines.ui.gank.tech.TechFragment;
import com.sujian.lines.ui.gank.tech.TechPresenter;
import com.sujian.lines.ui.gank.welfare.WelfareFragment;
import com.sujian.lines.ui.login.LoginActivity;
import com.sujian.lines.ui.user.UserActivity;

@ActivityFragmentInject(menuDefaultCheckedItem = R.id.drawer_gank, toolbarTitle = R.string.gank)
public class GankActivity extends BaseViewPagerActivity<GankPresenter, GankModel> implements GankContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        super.initView();
        mPresenter.getUserInfo();
    }

    @Override
    protected void initViewPager() {
        super.initViewPager();

        TechFragment androidFragment = new TechFragment();
        TechFragment iOSFragment = new TechFragment();
        TechFragment webFragment = new TechFragment();
        Bundle androidBundle = new Bundle();
        androidBundle.putString("tech", TechPresenter.TECH_ANDROID);
        androidFragment.setArguments(androidBundle);
        Bundle iosBundle = new Bundle();
        iosBundle.putString("tech", TechPresenter.TECH_IOS);
        iOSFragment.setArguments(iosBundle);
        Bundle webBundle = new Bundle();
        webBundle.putString("tech", TechPresenter.TECH_WEB);
        webFragment.setArguments(webBundle);

        WelfareFragment welfareFragment = new WelfareFragment();

        fragments.add(androidFragment);
        fragments.add(iOSFragment);
        fragments.add(webFragment);
        fragments.add(welfareFragment);

        titles.add("Android");
        titles.add("Ios");
        titles.add("Web");
        titles.add("Welfare");

        baseFragmentPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void initUserInfo(_User user) {
        ImageUtil.loadRoundImg(mContext, iv_face, user.getFace());
        tv_name.setText(user.getUsername());
        iv_face.setOnClickListener(v -> {
            if (SpUtil.getUser() == null) {
                new MaterialDialog.Builder(mContext)
                        .title("提示")
                        .content("骚年 你还没有登录，确定登录？")
                        .negativeText("取消")
                        .positiveText("确定")
                        .onPositive((dialog, which) -> startActivity(new Intent(mContext, LoginActivity.class)));
            } else {
                ActivityCompat.startActivity((Activity) mContext, new Intent(mContext, UserActivity.class).putExtra(C.HEAD_DATA, user)
                        , ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, iv_face, UserActivity.TRANSLATE_VIEW).toBundle());
            }
        });
    }
}
