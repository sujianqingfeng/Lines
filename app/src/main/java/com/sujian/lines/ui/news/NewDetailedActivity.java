package com.sujian.lines.ui.news;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.sujian.lines.C;
import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.base.util.ImageUtil;
import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.base.util.ToastUtil;
import com.sujian.lines.base.util.ViewUtil;
import com.sujian.lines.data.entity.Homeitem;

import butterknife.Bind;

public class NewDetailedActivity extends BaseActivity<NewDetailedPresenter, NewDetailedModel> implements NewDetailedContract.View {
    public static final String TRANSLATE_VIEW = "share_img";

    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_comment)
    EditText et_comment;
    @Bind(R.id.bt_comment)
    Button bt_comment;


    @Override
    public int getLayoutId() {
        return R.layout.activity_new_detailed;
    }

    @Override
    public void initView() {
        Homeitem mSubject = (Homeitem) getIntent().getSerializableExtra(C.HEAD_DATA);

        if (mSubject == null)
            return;


        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        ImageUtil.loadImg(image, mSubject.getUrl_img());
        setTitle(mSubject.getTitle());
        ViewCompat.setTransitionName(image, TRANSLATE_VIEW);



        bt_comment.setOnClickListener((view -> {
            String comment = et_comment.getText().toString().trim();
            if (!TextUtils.isEmpty(comment)) {
                mPresenter.createComment(
                        comment, mSubject.getNid(), SpUtil.getUser());
            } else {
                ToastUtil.show("评论内容不能为空！");
            }
        }));

    }


    @Override
    public void commentSuc() {
        ToastUtil.show("评论成功！");
        et_comment.setText("");
        ViewUtil.hideKeyboard(this);
    }

    @Override
    public void commentFail() {
        ToastUtil.show("评论失败");
    }

    @Override
    public void showLoginAction() {

    }

}
