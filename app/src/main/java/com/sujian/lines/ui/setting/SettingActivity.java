package com.sujian.lines.ui.setting;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sujian.lines.App;
import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.base.util.ACache;
import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.base.util.ToastUtil;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity<SettingPresenter, SettingModel> implements SettingContract.View {

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
        setToolBar(toolbar, "设置");
        scSettingCache.setChecked(SpUtil.getAutoCacheState());
        scSettingImage.setChecked(SpUtil.getNoImageState());
        scSettingNight.setChecked(SpUtil.isNight());

        File cacheFile = new File(App.getAppContext().getCacheDir(), "cache");
        tvSettingClear.setText(ACache.getCacheSize(cacheFile));


        try {
            PackageManager pm = getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
            String versionName = pi.versionName;
            tvSettingUpdate.setText("当前版本号 v" + versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    //点击事件
    @OnClick({R.id.ll_setting_feedback, R.id.ll_setting_clear, R.id.ll_setting_update})
    void clickEvent(View view) {
        switch (view.getId()) {
            case R.id.ll_setting_feedback:
                ToastUtil.show("骚年，不要急，功能还未实现");
                break;

            case R.id.ll_setting_clear:

                break;

            case R.id.ll_setting_update:
                ToastUtil.show("骚年，不要急，功能还未实现");
                break;
        }
    }

}
