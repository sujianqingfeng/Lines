package com.sujian.lines.ui.wechat.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.sujian.lines.R;
import com.sujian.lines.base.util.ImageUtil;
import com.sujian.lines.data.entity.WXItemBean;
import com.sujian.lines.ui.wechat.detail.TechDetailActivity;
import com.sujian.lines.view.layout.SquareImageView;

import java.util.List;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by codeest on 16/8/29.
 */

public class WechatAdapter extends RecyclerView.Adapter<WechatAdapter.ViewHolder>{

    private Context mContext;
    private LayoutInflater inflater;
    private List<WXItemBean> mList;

    public WechatAdapter(Context mContext, List<WXItemBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_wechat, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ImageUtil.loadImg(mContext,holder.ivImage,mList.get(position).getPicUrl());
        holder.tvTitle.setText(mList.get(position).getTitle());
        holder.tvFrom.setText(mList.get(position).getDescription());
        holder.tvTime.setText(mList.get(position).getCtime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mContext, TechDetailActivity.class);
                intent.putExtra("id",mList.get(holder.getAdapterPosition()).getPicUrl());   //wechat API 没有id，用图片来做唯一数据库索引
                intent.putExtra("title",mList.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("url",mList.get(holder.getAdapterPosition()).getUrl());
                intent.putExtra("tech", WeChatFPresenter.TECH_WECHAT);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_wechat_item_title)
        TextView tvTitle;
        @Bind(R.id.tv_wechat_item_time)
        TextView tvTime;
        @Bind(R.id.tv_wechat_item_from)
        TextView tvFrom;
        @Bind(R.id.iv_wechat_item_image)
        SquareImageView ivImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
