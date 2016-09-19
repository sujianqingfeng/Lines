package com.sujian.lines.ui.wechat;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.base.BaseMenuActivity;
import com.sujian.lines.base.BaseViewPagerActivity;
import com.sujian.lines.base.util.ActivityFragmentInject;
import com.sujian.lines.ui.wechat.fragment.WeChatFragment;
import com.sujian.lines.ui.zhihu.daily.DailyFragment;

import butterknife.Bind;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
@ActivityFragmentInject(menuDefaultCheckedItem = R.id.drawer_wechat,toolbarTitle = R.string.wechat)
public class WeChatActivity extends BaseViewPagerActivity<WeChatPresenter,WeChatModel> implements WeChatContract.View{



    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initViewPager() {
        super.initViewPager();
        WeChatFragment weChatFragment=new WeChatFragment();
        fragments.add(weChatFragment);
        titles.add("微信");
        baseFragmentPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void initView() {
        super.initView();
        tabLayout.setVisibility(View.GONE);
    }
}
