package com.sujian.lines.view.layout;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.banner.widget.Banner.BaseIndicatorBanner;
import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;
import com.sujian.lines.R;


public class SimpleGuideBanner extends BaseIndicatorBanner<Integer, SimpleGuideBanner> {
    public SimpleGuideBanner(Context context) {
        this(context, null, 0);
    }

    public SimpleGuideBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleGuideBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setBarShowWhenLast(false);
    }

    @Override
    public View onCreateItemView(int position) {
        View inflate = View.inflate(mContext, R.layout.adapter_simple_guide, null);
        ImageView iv = (ImageView) inflate.findViewById(R.id.iv);
        HTextView htv_jump = (HTextView) inflate.findViewById(R.id.htv_jump);

        final Integer resId = mDatas.get(position);
        htv_jump.setVisibility(position == mDatas.size() - 1 ? VISIBLE : GONE);
        if (position == mDatas.size()-1){
            htv_jump.setVisibility(VISIBLE);

        }else {
            htv_jump.setVisibility(GONE);
        }

        addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == mDatas.size()-1){
                    htv_jump.setAnimateType(HTextViewType.LINE);
                    htv_jump.animateText("立即体验"); // animate
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Glide.with(mContext)
                .load(resId)
                .into(iv);

        htv_jump.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onJumpClickL != null)
                    onJumpClickL.onJumpClick();
            }
        });

        return inflate;
    }

    private OnJumpClickL onJumpClickL;

    public interface OnJumpClickL {
        void onJumpClick();
    }

    public void setOnJumpClickL(OnJumpClickL onJumpClickL) {
        this.onJumpClickL = onJumpClickL;
    }
}
