package com.sujian.lines.ui.home;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.logger.Logger;
import com.sujian.lines.C;
import com.sujian.lines.R;
import com.sujian.lines.base.BaseViewPagerActivity;
import com.sujian.lines.base.RxBus;
import com.sujian.lines.base.RxManager;
import com.sujian.lines.base.util.ActivityFragmentInject;
import com.sujian.lines.base.util.ImageUtil;
import com.sujian.lines.base.util.ToastUtil;
import com.sujian.lines.data.entity._User;
import com.sujian.lines.data.event.TimeEvent;
import com.sujian.lines.ui.user.UserActivity;
import com.sujian.lines.ui.zhihu.daily.DailyFragment;
import com.sujian.lines.ui.zhihu.hot.HotFragment;
import com.sujian.lines.ui.zhihu.section.SectionFragment;
import com.sujian.lines.ui.zhihu.theme.ThemeFragment;

import java.util.Calendar;

import butterknife.Bind;
@ActivityFragmentInject(menuDefaultCheckedItem = R.id.drawer_zhihu,toolbarTitle = R.string.zhihu)
public class HomeActivity extends BaseViewPagerActivity<HomePresenter,HomeModel> implements HomeContract.View {

    @Bind(R.id.dl_main)
    DrawerLayout dlMainDrawer;
    @Bind(R.id.main_toorbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;


    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }



    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void initView() {
        super.initView();

        mPresenter.showTabs();
        mPresenter.getUserInfo();

        fab.setVisibility(View.VISIBLE);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0)  fab.setVisibility(View.VISIBLE);
                else fab.setVisibility(View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        fab.setOnClickListener((view -> {
            Calendar instance = Calendar.getInstance();
            int year = instance.get(Calendar.YEAR);
            int month=instance.get(Calendar.MONTH);
            int day=instance.get(Calendar.DAY_OF_MONTH);
            Logger.e("现在的时间"+year+"-"+(month+1)+"-"+day);
            DatePickerDialog dialog=new DatePickerDialog(mContext,new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Logger.e("设置的时间"+year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                    RxManager rx=new RxManager();
                    rx.post(C.EVENT_TIME,new TimeEvent(dayOfMonth,monthOfYear+1,year));
                }
            }, year, month, day);

            dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            dialog.show();
        }));
    }

    @Override
    protected void initViewPager() {
        super.initViewPager();
        DailyFragment dailyFragment=new DailyFragment();
        ThemeFragment themeFragment=new ThemeFragment();
        SectionFragment sectionFragment=new SectionFragment();
        HotFragment hotFragment=new HotFragment();

        fragments.add(dailyFragment);
        fragments.add(themeFragment);
        fragments.add(sectionFragment);
        fragments.add(hotFragment);
        titles.add("日报");
        titles.add("主题");
        titles.add("专栏");
        titles.add("热门");
        baseFragmentPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showTabs(String[] mTabs) {

    }

    @Override
    public void initUserInfo(_User user) {
        ImageUtil.loadRoundImg(mContext,iv_face, user.getFace());
        tv_name.setText(user.getUsername());
        iv_face.setOnClickListener(v ->
                ActivityCompat.startActivity((Activity) mContext, new Intent(mContext, UserActivity.class).putExtra(C.HEAD_DATA, user)
                        , ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, iv_face, UserActivity.TRANSLATE_VIEW).toBundle())
        );
    }


}
