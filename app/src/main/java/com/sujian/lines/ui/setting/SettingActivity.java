package com.sujian.lines.ui.setting;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sujian.lines.App;
import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.base.RxManager;
import com.sujian.lines.base.util.ACache;
import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.base.util.ToastUtil;
import com.sujian.lines.data.event.NightModeEvent;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 设置界面view
 */
public class SettingActivity extends BaseActivity<SettingPresenter, SettingModel> implements SettingContract.View,CompoundButton.OnCheckedChangeListener {

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

    private File cacheFile;

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

        //设置监听
        scSettingCache.setOnCheckedChangeListener(this);
        scSettingImage.setOnCheckedChangeListener(this);
        scSettingNight.setOnCheckedChangeListener(this);

        cacheFile = new File(App.getAppContext().getCacheDir(), "cache");
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
                ACache.deleteDir(cacheFile);
                tvSettingClear.setText(ACache.getCacheSize(cacheFile));
                break;

            case R.id.ll_setting_update:
                ToastUtil.show("骚年，不要急，功能还未实现");
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.sc_cache://自动缓存
               SpUtil.setAutoCacheState(isChecked);
                break;
            case R.id.sc_setting_image://无图模式
                SpUtil.setNoImageState(isChecked);
                break;
            case R.id.sc_setting_night://日夜间模式
                RxManager rxManager=new RxManager();
                rxManager.post("night",new NightModeEvent(!SpUtil.isNight()));
                break;
        }
    }
}
