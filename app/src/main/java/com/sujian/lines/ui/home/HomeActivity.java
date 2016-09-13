package com.sujian.lines.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sujian.lines.C;
import com.sujian.lines.R;
import com.sujian.lines.base.BaseViewPagerActivity;
import com.sujian.lines.base.util.ActivityFragmentInject;
import com.sujian.lines.base.util.ImageUtil;
import com.sujian.lines.data.entity._User;
import com.sujian.lines.ui.user.UserActivity;
import com.sujian.lines.ui.zhihu.daily.DailyFragment;
import com.sujian.lines.ui.zhihu.hot.HotFragment;
import com.sujian.lines.ui.zhihu.section.SectionFragment;
import com.sujian.lines.ui.zhihu.theme.ThemeFragment;

import butterknife.Bind;
@ActivityFragmentInject(menuDefaultCheckedItem = R.id.drawer_zhihu,toolbarTitle = R.string.zhihu)
public class HomeActivity extends BaseViewPagerActivity<HomePresenter,HomeModel> implements HomeContract.View {

    @Bind(R.id.dl_main)
    DrawerLayout dlMainDrawer;
    @Bind(R.id.main_toorbar)
    Toolbar toolbar;





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
        ImageUtil.loadRoundImg(iv_face, user.getFace());
        tv_name.setText(user.getUsername());
        iv_face.setOnClickListener(v ->
                ActivityCompat.startActivity((Activity) mContext, new Intent(mContext, UserActivity.class).putExtra(C.HEAD_DATA, user)
                        , ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, iv_face, UserActivity.TRANSLATE_VIEW).toBundle())
        );
    }


}
