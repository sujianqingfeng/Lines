package com.sujian.lines.ui.about;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;
import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.data.event.TimeEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class AboutMeActivity extends BaseActivity<AboutMePresenter, AboutMeModel> implements AboutMeContract.View {

    @Bind(R.id.tb_about)
    Toolbar tb_about;
    @Bind(R.id.rv_about)
    RecyclerView rv_about;
    AboutMeAdapter aboutMeAdapter;
    List<String> list;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_me;
    }

    @Override
    public void initView() {
        setToolBar(tb_about, "关于");
        list=new ArrayList<>();
        list.add("项目简介：Lines资讯，提供极具特色的资讯阅读，包括知乎日报、微信精选、干货集中营的最新资讯，实现比电脑" +
                "上看资讯更方便的体验。项目UI，仿照了GeekNews，MVP框架用了T-MVP,这两个项目github上面能够直接搜索到，感觉大神" +
                "们的分享。");

        list.add("作者：素笺");
        list.add("项目地址：https://github.com/sujianqingfeng/Lines.git");
        list.add("GeekNews: https://github.com/codeestX/GeekNews.git");
        list.add("T-MVP: https://github.com/north2014/T-MVP.git");
        list.add("版本：1.0");
        aboutMeAdapter=new AboutMeAdapter(mContext,list);

        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        rv_about.setAdapter(aboutMeAdapter);
        rv_about.setLayoutManager(manager);
    }
}
