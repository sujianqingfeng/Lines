package com.sujian.lines.ui.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;
import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.ui.guide.GuideActivity;

import butterknife.Bind;

public class SplashActivity extends BaseActivity {

    @Bind(R.id.htv_splash)
    HTextView htv_splash;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(mContext, GuideActivity.class));
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        htv_splash.setAnimateType(HTextViewType.LINE);
        htv_splash.animateText("L i n e s"); // animate
        handler.sendEmptyMessageDelayed(1,3000);
        finish();
    }
}
