package com.sujian.lines.ui.wechat;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sujian.lines.C;
import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.base.BaseMenuActivity;
import com.sujian.lines.base.BaseViewPagerActivity;
import com.sujian.lines.base.util.ActivityFragmentInject;
import com.sujian.lines.base.util.ImageUtil;
import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.data.entity._User;
import com.sujian.lines.ui.login.LoginActivity;
import com.sujian.lines.ui.user.UserActivity;
import com.sujian.lines.ui.wechat.fragment.WeChatFragment;
import com.sujian.lines.ui.zhihu.daily.DailyFragment;

import butterknife.Bind;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
@ActivityFragmentInject(menuDefaultCheckedItem = R.id.drawer_wechat, toolbarTitle = R.string.wechat)
public class WeChatActivity extends BaseViewPagerActivity<WeChatPresenter, WeChatModel> implements WeChatContract.View {


    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initViewPager() {
        super.initViewPager();
        WeChatFragment weChatFragment = new WeChatFragment();
        fragments.add(weChatFragment);
        titles.add("微信");
        baseFragmentPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void initView() {
        super.initView();
        tabLayout.setVisibility(View.GONE);
        mPresenter.getUserInfo();
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
                                .onPositive((dialog, which) -> startActivity(new Intent(mContext,LoginActivity.class)));
                    } else {
                        ActivityCompat.startActivity((Activity) mContext, new Intent(mContext, UserActivity.class).putExtra(C.HEAD_DATA, user)
                                , ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, iv_face, UserActivity.TRANSLATE_VIEW).toBundle());
                    }
                }

        );
    }
}
