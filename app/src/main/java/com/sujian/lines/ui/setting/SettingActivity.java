package com.sujian.lines.ui.setting;



import android.support.design.widget.TabLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity<SettingPresenter,SettingModel> implements SettingContract.View {

    @Bind(R.id.tb_setting)
    Toolbar toolbar;

    @Bind(R.id.sc_cache)
    SwitchCompat scSettingCache;
    @Bind(R.id.sc_setting_image)
    SwitchCompat scSettingImage;
    @Bind(R.id.sc_setting_night)
    SwitchCompat scSettingNight;
    @Bind(R.id.ll_setting_feedback)
    LinearLayout llSettingFeedback;
    @Bind(R.id.tv_setting_clear)
    TextView tvSettingClear;
    @Bind(R.id.ll_setting_clear)
    LinearLayout llSettingClear;
    @Bind(R.id.tv_setting_update)
    TextView tvSettingUpdate;
    @Bind(R.id.ll_setting_update)
    LinearLayout llSettingUpdate;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {








        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }





    public void finsh(View view){

    }
}
