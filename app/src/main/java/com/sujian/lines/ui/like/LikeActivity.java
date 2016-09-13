package com.sujian.lines.ui.like;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;

public class LikeActivity extends BaseActivity<LikePresenter,LikeModel> implements LikeContract.View {


    @Override
    public int getLayoutId() {
        return R.layout.activity_like;
    }

    @Override
    public void initView() {

    }
}
