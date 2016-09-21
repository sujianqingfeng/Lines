package com.sujian.lines.base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.logger.Logger;
import com.sujian.lines.App;
import com.sujian.lines.R;
import com.sujian.lines.base.util.ActivityFragmentInject;
import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.ui.about.AboutMeActivity;
import com.sujian.lines.ui.gank.activity.GankActivity;
import com.sujian.lines.ui.home.HomeActivity;
import com.sujian.lines.ui.like.LikeActivity;
import com.sujian.lines.ui.setting.SettingActivity;
import com.sujian.lines.ui.wechat.WeChatActivity;

import butterknife.Bind;

/**
 * 带有侧边栏的activity基类
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public abstract class BaseMenuActivity<T extends BasePresenter, E extends BaseModel> extends BaseActivity<T, E> {

    @Bind(R.id.dl_main)
    DrawerLayout dl_main;
    @Bind(R.id.nv_menu)
    protected NavigationView nv_menu;
    @Bind(R.id.main_toorbar)
    protected Toolbar main_toorbar;

    //viewpager
    @Bind(R.id.viewpager)
    protected ViewPager viewpager;
    //tablayout
    @Bind(R.id.tablayout)
    protected TabLayout tabLayout;

    protected int mMenuDefaultCheckedItem;
    protected int mToolbarTitle;
    protected ImageView iv_face;
    protected TextView tv_name;

    @Override
    public abstract int getLayoutId();


    @Override
    public void initView() {
        initAnnotation();
        initMenu();
        initToolBar();
        initHeadView();

    }

    private void initHeadView() {
        View view = nv_menu.inflateHeaderView(R.layout.nav_header_home);
        iv_face = (ImageView) view.findViewById(R.id.iv_face);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
    }


    @Override
    public void onBackPressed() {
        if (dl_main.isDrawerOpen(Gravity.LEFT)) dl_main.closeDrawers();
        else {
            new MaterialDialog.Builder(this)
                    .title("提示")
                    .content("骚年 你确定要退出程序？")
                    .positiveText("确定")
                    .negativeText("取消")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            App.getAppContext().exitApp();
                        }
                    })
                    .show();
        }
        ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            dl_main.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }


    /**
     * 初始化注解
     */
    private void initAnnotation() {
        if (getClass().isAnnotationPresent(ActivityFragmentInject.class)) {
            ActivityFragmentInject annotation = getClass()
                    .getAnnotation(ActivityFragmentInject.class);
            mToolbarTitle = annotation.toolbarTitle();
            mMenuDefaultCheckedItem = annotation.menuDefaultCheckedItem();
        } else {
            throw new RuntimeException(
                    "Class must add annotations of ActivityFragmentInitParams.class");
        }
    }

    /**
     * 初始化toolbar
     */
    private void initToolBar() {

        setSupportActionBar(main_toorbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, dl_main, main_toorbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        dl_main.setDrawerListener(mDrawerToggle);
    }


    /**
     * 初始化侧边菜单
     */
    private void initMenu() {
        if (mMenuDefaultCheckedItem != -1 && mMenuDefaultCheckedItem != 0) {

            main_toorbar.setTitle(mToolbarTitle);
            nv_menu.setCheckedItem(mMenuDefaultCheckedItem);
        }

        nv_menu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Class mClass = null;
                switch (item.getItemId()) {
                    case R.id.drawer_zhihu:
                        mClass = HomeActivity.class;
                        break;
                    case R.id.drawer_wechat:
                        mClass = WeChatActivity.class;
                        break;
                    case R.id.drawer_gank:
                        mClass = GankActivity.class;
                        break;

                    case R.id.drawer_like:
                        mClass = LikeActivity.class;
                        break;

                    case R.id.drawer_setting:
                        mClass = SettingActivity.class;
                        break;

                    case R.id.drawer_about:
                        mClass = AboutMeActivity.class;
                        break;
                    case R.id.drawer_toggle:
                        SpUtil.setNight(mContext, !SpUtil.isNight());
                        break;
                }
                dl_main.closeDrawers();
                meunStartActivity(mClass);
                return false;
            }
        });
    }




    //页面跳转
    public void meunStartActivity(Class<?> cls) {
        if (cls != null) {
            Intent intent = new Intent();
            intent.setClass(mContext, cls);
            // 此标志用于启动一个Activity的时候，若栈中存在此Activity实例，则把它调到栈顶。不创建多一个
            //如果要启动的flags 已经finsh  不能启动
            //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            this.startActivity(intent);
            Logger.e("跳转完成" + "-->" + cls.getSimpleName().toString());
            this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //finish();
        }
    }
}
