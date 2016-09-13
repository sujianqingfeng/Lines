package com.sujian.lines.ui.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;

public class SettingActivity extends BaseActivity<SettingPresenter,SettingModel> implements SettingContract.View {


    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {

    }
}
