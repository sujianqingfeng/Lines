package com.sujian.lines.view.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseViewHolder;
import com.sujian.lines.data.entity.Homeitem;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.Bind;

/**
 * 新闻内容
 * Created by sujian on 2016/9/11.
 * Mail:121116111@qq.com
 */
public class NewDetailedHeaderVH extends BaseViewHolder<Homeitem>{
    @Bind(R.id.tv_content)
    TextView tv_content;
    public NewDetailedHeaderVH(View v) {
        super(v);
    }

    @Override
    public int getType() {
        return R.layout.list_item_deatiled;
    }

    @Override
    public void onBindViewHolder(View view, Homeitem obj) {
        String content = obj.getContent().replace("<br>", "\n").replaceAll(" ", "").replaceAll("//", "");
        if (!TextUtils.isEmpty(content)) {
            content = content.substring(content.indexOf("&gt;") + 4, content.length());
            tv_content.setText(content);
        }


    }
}
