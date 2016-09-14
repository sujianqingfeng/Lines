package com.sujian.lines.base;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.sujian.lines.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 带有viewpager 和 侧边 的activity 的基类
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public abstract class BaseViewPagerActivity<T extends BasePresenter, E extends BaseModel> extends BaseMenuActivity<T,E>{

    //viewpager
    @Bind(R.id.viewpager)
    protected
    ViewPager viewpager;
    //tablayout
    @Bind(R.id.tablayout)
    TabLayout tabLayout;

    //装载fragment
    protected List<SupportFragment> fragments;
    //装载标题
    protected List<String> titles;
    //viewpager的适配器
    protected BaseFragmentPagerAdapter baseFragmentPagerAdapter;

    @Override
    public  abstract int getLayoutId();



    @Override
    public void initView() {
        super.initView();
        initViewPager();
    }


    /**
     * 初始化viewpager
     */
    protected void initViewPager() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();

        baseFragmentPagerAdapter = new BaseFragmentPagerAdapter<>(
                getSupportFragmentManager(),
                fragments,
                titles);
        viewpager.setAdapter(baseFragmentPagerAdapter);


        initTabLayout();
    }

    /**
     * 初始化tablayout
     */
    protected void initTabLayout() {
        tabLayout.setupWithViewPager(viewpager);
    }

}
