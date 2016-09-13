package com.sujian.lines.ui.gank.activity;

import android.view.View;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseViewPagerActivity;
import com.sujian.lines.base.util.ActivityFragmentInject;
import com.sujian.lines.ui.gank.android.AndroidFragment;
import com.sujian.lines.ui.gank.ios.IosFragment;
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

        AndroidFragment androidFragment=new AndroidFragment();
        IosFragment iosFragment=new IosFragment();
        WebFragment webFragment=new WebFragment();
        WelfareFragment welfareFragment=new WelfareFragment();

        fragments.add(androidFragment);
        fragments.add(iosFragment);
        fragments.add(webFragment);
        fragments.add(welfareFragment);

        titles.add("Android");
        titles.add("Ios");
        titles.add("Web");
        titles.add("Welfare");

        baseFragmentPagerAdapter.notifyDataSetChanged();
    }
}
