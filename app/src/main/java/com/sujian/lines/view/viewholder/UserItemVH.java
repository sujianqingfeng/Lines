package com.sujian.lines.view.viewholder;

import android.view.View;
import android.widget.TextView;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseViewHolder;
import com.sujian.lines.data.entity._User;

import butterknife.Bind;

/**
 * Created by sujian on 2016/9/12.
 * Mail:121116111@qq.com
 */
public class UserItemVH extends BaseViewHolder<_User> {

    @Bind(R.id.tv_title)
    TextView title;
    @Bind(R.id.tv_content)
    TextView content;


    public UserItemVH(View v) {
        super(v);
    }

    @Override
    public int getType() {
        return R.layout.list_item_userinfo;
    }

    @Override
    public void onBindViewHolder(View view, _User obj) {
        title.setText("用户名：");
        content.setText(obj.getUsername());
    }
}
