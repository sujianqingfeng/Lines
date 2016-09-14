package com.sujian.lines.ui.zhihu.hot;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.sujian.lines.R;
import com.sujian.lines.base.util.ImageUtil;
import com.sujian.lines.data.entity.HotListBean;
import com.sujian.lines.view.layout.SquareImageView;

import java.util.List;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by codeest on 16/8/21.
 */

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder>{

    private List<HotListBean.RecentBean> mList;
    private Context mContext;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public HotAdapter(Context mContext, List<HotListBean.RecentBean> mList) {
        this.mList = mList;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_daily, parent, false));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.title.setText(mList.get(position).getTitle());
        if (mList.get(position).getReadState()) {
            holder.title.setTextColor(ContextCompat.getColor(mContext, R.color.news_read));
        } else {
            holder.title.setTextColor(ContextCompat.getColor(mContext,R.color.news_unread));
        }

        ImageUtil.loadImg(holder.image,mList.get(position).getThumbnail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null) {
                    ImageView iv = (ImageView) view.findViewById(R.id.iv_daily_item_image);
                    onItemClickListener.onItemClick(holder.getAdapterPosition(),iv);
                }
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_daily_item_title)
        TextView title;
        @Bind(R.id.iv_daily_item_image)
        SquareImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    public void setReadState(int position,boolean readState) {
        mList.get(position).setReadState(readState);
    }
}
