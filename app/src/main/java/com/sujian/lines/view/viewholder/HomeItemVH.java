package com.sujian.lines.view.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sujian.lines.C;
import com.sujian.lines.R;
import com.sujian.lines.base.BaseViewHolder;
import com.sujian.lines.base.util.ImageUtil;
import com.sujian.lines.data.repository.HomeitemRepository;
import com.sujian.lines.ui.news.NewDetailedActivity;

import butterknife.Bind;

/**
 * 主页item
 * Created by sujian on 2016/9/11.
 * Mail:121116111@qq.com
 */
public class HomeItemVH extends BaseViewHolder<HomeitemRepository>{
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_source)
    TextView tv_source;
    @Bind(R.id.tv_info)
    TextView tv_info;
    @Bind(R.id.tv_time)
    TextView tv_time;
    public HomeItemVH(View v) {
        super(v);
    }

    @Override
    public int getType() {
        return R.layout.list_item_card_home;
    }

    @Override
    public void onBindViewHolder(View view, final HomeitemRepository obj) {
        ImageUtil.loadImg(image,obj.data.getUrl_img());
        tv_title.setText(obj.data.getTitle());
        tv_source.setText(obj.data.getSources());
        tv_time.setText(obj.data.getDate());
        tv_info.setText(obj.data.getDesc());
        view.setOnClickListener((v ->
                ActivityCompat.startActivity((Activity) mContext, new Intent(mContext, NewDetailedActivity.class).putExtra(C.HEAD_DATA, obj.data)
                        , ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, image, NewDetailedActivity.TRANSLATE_VIEW).toBundle())
        ));
    }
}
