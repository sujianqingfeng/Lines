package com.sujian.lines.ui.like;

import android.content.Context;
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
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sujian on 2016/9/27.
 * Mail:121116111@qq.com
 */

public class LikeAdapter extends SwipeMenuAdapter<LikeAdapter.ViewHolder> {

    private List<Like> restules;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private Context context;


    public LikeAdapter(Context context, List<Like> restules) {
        this.restules = restules;
        this.context=context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return inflater.inflate(R.layout.item_like,parent,false);
    }

    @Override
    public ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new ViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Like like = restules.get(position);

        holder.tv_title.setText(like.getTitle());
       switch (like.getType()){
           case C.TYPE_ZHIHU:
               ImageUtil.loadImg(context,holder.imageView,like.getImage());
               holder.tv_source.setText("来自 知乎");
               break;

           case C.TYPE_ANDROID:
               holder.tv_source.setText("来自 Android");
               holder.imageView.setBackgroundResource(R.mipmap.ic_android);
               break;

           case C.TYPE_IOS:
               holder.imageView.setBackgroundResource(R.mipmap.ic_ios);
               holder.tv_source.setText("来自 IOS");
               break;

           case C.TYPE_WEB:
               holder.imageView.setBackgroundResource(R.mipmap.ic_web);
               holder.tv_source.setText("来自 Web");
               break;

           case C.TYPE_WECHAT:
               ImageUtil.loadImg(context,holder.imageView,like.getLid());
               holder.tv_source.setText("来自 微信");
               break;
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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }
}
