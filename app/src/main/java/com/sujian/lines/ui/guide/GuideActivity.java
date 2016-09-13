package com.sujian.lines.ui.guide;

import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.flyco.banner.anim.select.ZoomInEnter;
import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.base.util.StatusBarUtil;
import com.sujian.lines.ui.login.LoginActivity;
import com.sujian.lines.ui.register.RegisterActivity;
import com.sujian.lines.view.layout.SimpleGuideBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by sujian on 2016/9/5.
 * Mail:121116111@qq.com
 */
public class GuideActivity extends BaseActivity {

    @Bind(R.id.sgb_guide)
    SimpleGuideBanner sgb_guide;

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {



    List<Integer> guideList=new ArrayList<>();
        guideList.add(R.mipmap.guide_01);
        guideList.add(R.mipmap.guide_02);
        guideList.add(R.mipmap.guide_03);

        sgb_guide
                .setIndicatorWidth(6)
                .setIndicatorHeight(6)
                .setIndicatorGap(12)
                .setIndicatorCornerRadius(3.5f)
                .setSelectAnimClass(ZoomInEnter.class)
                .barPadding(0, 10, 0, 10)
                .setSource(guideList)
                .startScroll();

        sgb_guide.setOnJumpClickL(new SimpleGuideBanner.OnJumpClickL() {
            @Override
            public void onJumpClick() {
                SpUtil.setFirst();
                startActivity(new Intent(mContext, LoginActivity.class));
                finish();
            }
        });
    }
}
