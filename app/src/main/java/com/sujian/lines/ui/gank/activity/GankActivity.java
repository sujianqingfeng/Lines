package com.sujian.lines.ui.gank.activity;

import android.os.Bundle;
import android.view.View;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseViewPagerActivity;
import com.sujian.lines.base.util.ActivityFragmentInject;
import com.sujian.lines.ui.gank.android.AndroidFragment;
import com.sujian.lines.ui.gank.ios.IosFragment;
import com.sujian.lines.ui.gank.tech.TechFragment;
import com.sujian.lines.ui.gank.tech.TechPresenter;
import com.sujian.lines.ui.gank.web.WebFragment;
import com.sujian.lines.ui.gank.welfare.WelfareFragment;

@ActivityFragmentInject(menuDefaultCheckedItem = R.id.drawer_gank,toolbarTitle = R.string.gank)
public class GankActivity extends BaseViewPagerActivity<GankPresenter,GankModel> implements GankContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        super.initView();
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

        WelfareFragment welfareFragment=new WelfareFragment();

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
}
