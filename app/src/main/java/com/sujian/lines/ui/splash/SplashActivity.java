package com.sujian.lines.ui.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;
import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.ui.guide.GuideActivity;
import com.sujian.lines.ui.home.HomeActivity;

import butterknife.Bind;

public class SplashActivity extends BaseActivity {

    @Bind(R.id.htv_splash)
    HTextView htv_splash;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    startActivity(new Intent(mContext, GuideActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(mContext, HomeActivity.class));
                    break;
            }

            finish();
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

        if (SpUtil.getFirst()){
            handler.sendEmptyMessageDelayed(1,3000);
        }else {
            handler.sendEmptyMessageDelayed(2,3000);
        }
    }
}
