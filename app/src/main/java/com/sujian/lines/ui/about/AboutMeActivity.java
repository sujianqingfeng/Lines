package com.sujian.lines.ui.about;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;

public class AboutMeActivity extends BaseActivity<AboutMePresenter,AboutMeModel> implements AboutMeContract.View {


    @Override
    public int getLayoutId() {
        return R.layout.activity_about_me;
    }

    @Override
    public void initView() {

    }
}
