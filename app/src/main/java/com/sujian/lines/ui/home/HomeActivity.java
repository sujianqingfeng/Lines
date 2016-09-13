package com.sujian.lines.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sujian.lines.C;
import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.base.BaseListFragment;
import com.sujian.lines.base.util.ImageUtil;
import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.base.util.helper.FragmentAdapter;
import com.sujian.lines.data.entity._User;
import com.sujian.lines.ui.news.NewDetailedActivity;
import com.sujian.lines.ui.user.UserActivity;
import com.sujian.lines.view.viewholder.HomeItemVH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import rx.Observable;

public class HomeActivity extends BaseActivity<HomePresenter,HomeModel> implements HomeContract.View {

    @Bind(R.id.nv_home_navigation)
    NavigationView nvMainNavigation;
    @Bind(R.id.dl_home)
    DrawerLayout dlMainDrawer;
    @Bind(R.id.tb_home)
    Toolbar toolbar;
    @Bind(R.id.tl_home)
    TabLayout tabs;
    @Bind(R.id.viewpager_home)
    ViewPager viewpager;

    private ImageView iv_face;
    private TextView tv_name;


    @Override
    public void onBackPressed() {
        if (dlMainDrawer.isDrawerOpen(Gravity.LEFT)) dlMainDrawer.closeDrawers();
        else super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            dlMainDrawer.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, dlMainDrawer, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        dlMainDrawer.setDrawerListener(mDrawerToggle);

        View view = nvMainNavigation.inflateHeaderView(R.layout.nav_header_home);
        iv_face= (ImageView) view.findViewById(R.id.iv_face);
        tv_name= (TextView) view.findViewById(R.id.tv_name);


        nvMainNavigation.setNavigationItemSelectedListener(item -> {
            item.setChecked(true);
            switch (item.getItemId()){
                case R.id.nav_setting:

                    break;
                case R.id.nav_switch:
                    SpUtil.setNight(mContext, !SpUtil.isNight());
                    break;
                case R.id.nav_pic:

                    break;
            }
            return true;
        });


    }

    @Override
    public void showTabs(String[] mTabs) {
        List<Fragment> fragments = new ArrayList<>();
        Observable.from(mTabs).subscribe(tab -> fragments.add(BaseListFragment.newInstance(HomeItemVH.class, tab)));
        viewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments, Arrays.asList(mTabs)));
        tabs.setupWithViewPager(viewpager);
        tabs.setTabsFromPagerAdapter(viewpager.getAdapter());
    }

    @Override
    public void initUserInfo(_User user) {
        ImageUtil.loadRoundImg(iv_face, user.getFace());
        tv_name.setText(user.getUsername());
        iv_face.setOnClickListener(v ->
            ActivityCompat.startActivity((Activity) mContext, new Intent(mContext, UserActivity.class).putExtra(C.HEAD_DATA, user)
                    , ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, iv_face, UserActivity.TRANSLATE_VIEW).toBundle())
        );
    }
}
