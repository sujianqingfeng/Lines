package com.sujian.lines.ui.zhihu.daily;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.sujian.lines.R;
import com.sujian.lines.base.util.ImageUtil;
import com.sujian.lines.data.entity.DailyListBean;
import com.sujian.lines.ui.zhihu.activity.ZhihuDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codeest on 16/8/13.
 */

public class TopPagerAdapter extends PagerAdapter {

    private List<DailyListBean.TopStoriesBean> mList = new ArrayList<>();
    private Context mContext;

    public TopPagerAdapter(Context context, List<DailyListBean.TopStoriesBean> mList)
    {
        this.mList = mList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_top_pager, container, false);
        ImageView ivImage = (ImageView) view.findViewById(R.id.iv_top_image);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_top_title);

        ImageUtil.loadImg(ivImage,mList.get(position).getImage());
        tvTitle.setText(mList.get(position).getTitle());
        final int id = mList.get(position).getId();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, ZhihuDetailActivity.class);
                intent.putExtra("id",id);
                mContext.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
