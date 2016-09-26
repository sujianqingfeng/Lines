package com.sujian.lines.ui.like;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;

import butterknife.Bind;

public class LikeActivity extends BaseActivity<LikePresenter,LikeModel> implements LikeContract.View {

    @Bind(R.id.tb_like)
    Toolbar tb_like;


    @Override
    public int getLayoutId() {
        return R.layout.activity_like;
    }

    @Override
    public void initView() {
        setToolBar(tb_like,"收藏");
    }
}
