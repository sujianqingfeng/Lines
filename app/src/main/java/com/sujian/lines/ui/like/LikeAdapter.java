package com.sujian.lines.ui.like;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sujian.lines.C;
import com.sujian.lines.R;
import com.sujian.lines.base.util.ImageUtil;
import com.sujian.lines.data.Data;
import com.sujian.lines.data.entity.Like;
import com.sujian.lines.ui.gank.detail.GirlDetailActivity;
import com.sujian.lines.ui.gank.tech.TechPresenter;
import com.sujian.lines.ui.wechat.detail.TechDetailActivity;
import com.sujian.lines.ui.zhihu.detail.ZhihuDetailActivity;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sujian on 2016/9/27.
 * Mail:121116111@qq.com
 */

public class LikeAdapter extends SwipeMenuAdapter<RecyclerView.ViewHolder> {

    private List<Like> restules;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private Context mContext;

    public LikeAdapter(Context context, List<Like> restules) {
        this.restules = restules;
        this.mContext=context;
        inflater = LayoutInflater.from(context);
    }


    //返回的类型是图片就返回1 其他都返回0
    @Override
    public int getItemViewType(int position) {
        Like like = restules.get(position);
        if (like.getType()==C.TYPE_GIRL)
            return 1;
        return 0;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        if (viewType==1)
            return inflater.inflate(R.layout.item_like_girl,parent,false);
        return inflater.inflate(R.layout.item_like,parent,false);
    }

    @Override
    public RecyclerView.ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        if (viewType==1)
            return new GirlViewHolder(realContentView);
        return new ViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Like like = restules.get(position);

        if (holder instanceof ViewHolder){
            ViewHolder viewHolder= (ViewHolder) holder;
            viewHolder.tv_title.setText(like.getTitle());
            switch (like.getType()){
                case C.TYPE_ZHIHU:
                    ImageUtil.loadImg(mContext,viewHolder.imageView,like.getImage());
                    viewHolder.tv_source.setText("来自 知乎");
                    viewHolder.itemView.setOnClickListener(v -> gotoDailyDetail(Integer.parseInt(like.getLid())));
                    break;

                case C.TYPE_ANDROID:
                    viewHolder.tv_source.setText("来自 Android");
                    viewHolder.imageView.setBackgroundResource(R.mipmap.ic_android);
                    viewHolder.itemView.setOnClickListener(v -> gotoTechDetail(like.getImage(),like.getTitle(),like.getLid(), TechPresenter.TECH_ANDROID));
                    break;

                case C.TYPE_IOS:
                    viewHolder.imageView.setBackgroundResource(R.mipmap.ic_ios);
                    viewHolder.tv_source.setText("来自 IOS");
                    viewHolder.itemView.setOnClickListener(v -> gotoTechDetail(like.getImage(),like.getTitle(),like.getLid(), TechPresenter.TECH_IOS));
                    break;

                case C.TYPE_WEB:
                    viewHolder.imageView.setBackgroundResource(R.mipmap.ic_web);
                    viewHolder.tv_source.setText("来自 Web");
                    viewHolder.itemView.setOnClickListener(v -> gotoTechDetail(like.getImage(),like.getTitle(),like.getLid(), TechPresenter.TECH_WEB));
                    break;

                case C.TYPE_WECHAT:
                    ImageUtil.loadImg(mContext,viewHolder.imageView,like.getLid());
                    viewHolder.tv_source.setText("来自 微信");
                    viewHolder.itemView.setOnClickListener(v -> gotoTechDetail(like.getImage(),like.getTitle(),like.getLid(), TechPresenter.TECH_WEB));
                    break;
            }
        }else {
            GirlViewHolder viewHolder= (GirlViewHolder) holder;
            ImageUtil.loadImg(mContext,viewHolder.imageView,like.getImage());
            viewHolder.tv_source.setText("来自 Welfare");
            viewHolder.itemView.setOnClickListener(v -> gotoGirlDetail(like.getImage(),like.getLid()));

        }


    }

    @Override
    public int getItemCount() {
        return restules == null ? 0 : restules.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_item_like)
        ImageView imageView;
        @Bind(R.id.tv_item_source)
        TextView tv_source;
        @Bind(R.id.tv_itme_title)
        TextView tv_title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class GirlViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_item_like)
        ImageView imageView;
        @Bind(R.id.tv_item_source)
        TextView tv_source;

        public GirlViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }


    public void gotoDailyDetail(int id) {
        Intent intent = new Intent();
        intent.setClass(mContext, ZhihuDetailActivity.class);
        intent.putExtra("id",id);
        mContext.startActivity(intent);
    }

    public void gotoTechDetail(String url,String title,String id,String tech) {
        Intent intent = new Intent();
        intent.setClass(mContext, TechDetailActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        intent.putExtra("id",id);
        intent.putExtra("tech",tech);
        mContext.startActivity(intent);
    }

    public void gotoGirlDetail(String url,String id) {
        Intent intent = new Intent();
        intent.setClass(mContext, GirlDetailActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("id",id);
        mContext.startActivity(intent);
    }
}
