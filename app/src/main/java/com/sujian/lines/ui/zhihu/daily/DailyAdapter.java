package com.sujian.lines.ui.zhihu.daily;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sujian.lines.R;
import com.sujian.lines.base.util.ImageUtil;
import com.sujian.lines.data.entity.DailyBeforeListBean;
import com.sujian.lines.data.entity.DailyListBean;
import com.sujian.lines.view.layout.SquareImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by codeest on 16/8/13.
 *
 * 一开始打算用ScrollView嵌套RecyclerView来实现
 * 但是RecyclerView23.1.1之后的版本嵌套会显示不全
 * Google也不推荐ScrollView嵌套RecyclerView
 * 还是采取getItemViewType来实现
 */

public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<DailyListBean.StoriesBean> mList;
    private List<DailyListBean.TopStoriesBean> mTopList;
    private LayoutInflater inflater;
    private Context mContext;
    private TopPagerAdapter mAdapter;
    private ViewPager topViewPager;
    private OnItemClickListener onItemClickListener;

    private boolean isBefore = false;
    private String currentTitle = "今日热闻";

    public enum ITEM_TYPE {
        ITEM_TOP,       //滚动栏
        ITEM_DATE,      //日期
        ITEM_CONTENT    //内容
    }

    public DailyAdapter(Context mContext, List<DailyListBean.StoriesBean> mList) {
        this.mList = mList;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if(!isBefore) {
            if(position == 0) {
                return ITEM_TYPE.ITEM_TOP.ordinal();
            } else if(position == 1) {
                return ITEM_TYPE.ITEM_DATE.ordinal();
            } else {
                return ITEM_TYPE.ITEM_CONTENT.ordinal();
            }
        } else {
            if(position == 0) {
                return ITEM_TYPE.ITEM_DATE.ordinal();
            } else {
                return ITEM_TYPE.ITEM_CONTENT.ordinal();
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE.ITEM_TOP.ordinal()) {
            mAdapter = new TopPagerAdapter(mContext,mTopList);
            return new TopViewHolder(inflater.inflate(R.layout.item_top, parent, false));
        } else if(viewType == ITEM_TYPE.ITEM_DATE.ordinal()) {
            return new DateViewHolder(inflater.inflate(R.layout.item_date, parent, false));
        }
        return new ContentViewHolder(inflater.inflate(R.layout.item_daily, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContentViewHolder) {
            final int contentPosition;
            if(isBefore) {
                contentPosition = position - 1;
            } else {
                contentPosition = position - 2;
            }
            ((ContentViewHolder)holder).title.setText(mList.get(contentPosition).getTitle());
            if (mList.get(contentPosition).getReadState()) {
                ((ContentViewHolder)holder).title.setTextColor(ContextCompat.getColor(mContext,R.color.news_read));
            } else {
                ((ContentViewHolder)holder).title.setTextColor(ContextCompat.getColor(mContext,R.color.news_unread));
            }

            ImageUtil.loadImg(((ContentViewHolder)holder).image,mList.get(contentPosition).getImages().get(0));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener != null) {
                        ImageView iv = (ImageView) view.findViewById(R.id.iv_daily_item_image);
                        onItemClickListener.onItemClick(contentPosition,iv);
                    }
                }
            });
        } else if (holder instanceof DateViewHolder) {
            ((DateViewHolder) holder).tvDate.setText(currentTitle);
        } else {
            ((TopViewHolder) holder).vpTop.setAdapter(mAdapter);
            topViewPager = ((TopViewHolder) holder).vpTop;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_daily_item_title)
        TextView title;
        @Bind(R.id.iv_daily_item_image)
        SquareImageView image;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public static class DateViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_daily_date)
        TextView tvDate;

        public DateViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public static class TopViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.vp_top)
        ViewPager vpTop;
        @Bind(R.id.ll_point_container)
        LinearLayout llContainer;

        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void addDailyDate(DailyListBean info) {
        currentTitle = "今日热闻";
        mList = info.getStories();
        mTopList = info.getTop_stories();
        isBefore = false;
        notifyDataSetChanged();
    }

    public void addDailyBeforeDate(DailyBeforeListBean info) {
        currentTitle = info.getDate();
        mList = info.getStories();
        isBefore = true;
        notifyDataSetChanged();
    }

    public boolean getIsBefore() {
        return isBefore;
    }

    public void setReadState(int position,boolean readState) {
        mList.get(position).setReadState(readState);
    }

    public void changeTopPager(int currentCount) {
        if(!isBefore && topViewPager != null) {
            topViewPager.setCurrentItem(currentCount);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }
}
