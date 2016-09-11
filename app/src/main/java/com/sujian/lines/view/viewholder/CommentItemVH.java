package com.sujian.lines.view.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseViewHolder;
import com.sujian.lines.base.util.ImageUtil;
import com.sujian.lines.data.repository.CommentInfoRepository;

import butterknife.Bind;

/**
 * 评论item viewholder
 * Created by sujian on 2016/9/11.
 * Mail:121116111@qq.com
 */
public class CommentItemVH extends BaseViewHolder<CommentInfoRepository>{

    @Bind(R.id.tv_content)
    TextView tv_content;
    @Bind(R.id.im_user)
    ImageView im_user;
    public CommentItemVH(View v) {
        super(v);
    }

    @Override
    public int getType() {
        return R.layout.list_item_comment;
    }

    @Override
    public void onBindViewHolder(View view, CommentInfoRepository obj) {
        if (obj.equals("")){

        }else {
            tv_content.setText(obj.data.content);
            ImageUtil.loadRoundImg(im_user,obj.data.creater.getFace());
        }
    }
}
