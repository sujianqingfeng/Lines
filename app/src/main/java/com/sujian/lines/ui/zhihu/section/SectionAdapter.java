package com.sujian.lines.ui.zhihu.section;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.sujian.lines.App;
import com.sujian.lines.R;
import com.sujian.lines.base.util.ImageUtil;
import com.sujian.lines.base.util.ViewUtil;
import com.sujian.lines.data.entity.SectionListBean;
import com.sujian.lines.ui.zhihu.section.activity.SectionActivity;

import java.util.List;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by codeest on 16/8/21.
 */

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder>{

    private Context mContext;
    private List<SectionListBean.DataBean> mList;
    private LayoutInflater inflater;

    public SectionAdapter(Context mContext, List<SectionListBean.DataBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_section,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //Glide在加载GridView等时,由于ImageView和Bitmap实际大小不符合,第一次时加载可能会变形(我这里出现了放大),必须在加载前再次固定ImageView大小
//        ViewGroup.LayoutParams lp = holder.ivBg.getLayoutParams();
//        lp.width = (App.SCREEN_WIDTH - ViewUtil.dp2px(mContext,12)) / 2;
//        lp.height = ViewUtil.dp2px(mContext,120);

        ImageUtil.loadImg(mContext,holder.ivBg,mList.get(position).getThumbnail());

        holder.tvKind.setText(mList.get(position).getName());
        holder.tvDes.setText(mList.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mContext, SectionActivity.class);
                intent.putExtra("id",mList.get(holder.getAdapterPosition()).getId());
                intent.putExtra("title",mList.get(holder.getAdapterPosition()).getName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.section_bg)
        ImageView ivBg;
        @Bind(R.id.section_kind)
        TextView tvKind;
        @Bind(R.id.section_des)
        TextView tvDes;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
