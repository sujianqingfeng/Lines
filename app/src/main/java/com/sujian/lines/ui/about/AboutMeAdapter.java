package com.sujian.lines.ui.about;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sujian.lines.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sujian on 2016/9/26.
 * Mail:121116111@qq.com
 */

public class AboutMeAdapter extends RecyclerView.Adapter<AboutMeAdapter.ViewHolder>{

    private List<String> list;
    private LayoutInflater inflater;

    public AboutMeAdapter(Context context,List<String> list) {
        this.list = list;
        inflater= LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_about,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.info.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.tv_about_info)
        TextView info;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
