package com.sujian.lines.ui.wechat;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.base.BaseMenuActivity;
import com.sujian.lines.base.util.ActivityFragmentInject;

import butterknife.Bind;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
@ActivityFragmentInject(menuDefaultCheckedItem = R.id.drawer_wechat,toolbarTitle = R.string.wechat)
public class WeChatActivity extends BaseMenuActivity<WeChatPresenter,WeChatModel> implements WeChatContract.View{

    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        super.initView();
        tablayout.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);
    }
}
