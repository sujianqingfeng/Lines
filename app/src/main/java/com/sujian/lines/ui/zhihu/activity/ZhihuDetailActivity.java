package com.sujian.lines.ui.zhihu.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.base.util.HtmlUtil;
import com.sujian.lines.base.util.ImageUtil;
import com.sujian.lines.base.util.NetWorkUtil;
import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.data.entity.DetailExtraBean;
import com.sujian.lines.data.entity.ZhihuDetailBean;
import com.wang.avi.AVLoadingIndicatorView;


import butterknife.Bind;

public class ZhihuDetailActivity extends BaseActivity<ZhihuDetailPresenter,ZhihuDetailModel> implements ZhihuDetailContract.View {

    @Bind(R.id.detail_bar_image)
    ImageView detailBarImage;
    @Bind(R.id.detail_bar_copyright)
    TextView detailBarCopyright;
    @Bind(R.id.view_toolbar)
    Toolbar viewToolbar;
    @Bind(R.id.clp_toolbar)
    CollapsingToolbarLayout clpToolbar;
    @Bind(R.id.wv_detail_content)
    WebView wvDetailContent;
    @Bind(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    @Bind(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    @Bind(R.id.tv_detail_bottom_like)
    TextView tvDetailBottomLike;
    @Bind(R.id.tv_detail_bottom_comment)
    TextView tvDetailBottomComment;
    @Bind(R.id.tv_detail_bottom_share)
    TextView tvDetailBottomShare;
    @Bind(R.id.ll_detail_bottom)
    FrameLayout llDetailBottom;
    @Bind(R.id.fab_like)
    FloatingActionButton fabLike;



    int id = 0;
    int allNum = 0;
    int shortNum = 0;
    int longNum = 0;
    String shareUrl;
    String imgUrl;
    boolean isBottomShow = true;
    boolean isImageShow = false;
    boolean isTransitionEnd = false;


    @Override
    public int getLayoutId() {
        return R.layout.activity_zhihu_detail;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView() {
        setToolBar(viewToolbar,"");
        Intent intent = getIntent();
        id = intent.getExtras().getInt("id");
        mPresenter.queryLikeData(id);
        mPresenter.getDetailData(id);
        mPresenter.getExtraData(id);

        WebSettings settings = wvDetailContent.getSettings();
        if (SpUtil.getNoImageState()) {
            settings.setBlockNetworkImage(true);
        }
        if (SpUtil.getAutoCacheState()) {
            settings.setAppCacheEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setDatabaseEnabled(true);
            if (NetWorkUtil.isNetConnected(mContext)) {
                settings.setCacheMode(WebSettings.LOAD_DEFAULT);
            } else {
                settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
            }
        }
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        wvDetailContent.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        nsvScroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY - oldScrollY > 0 && isBottomShow) {  //下移隐藏
                    isBottomShow = false;
                    llDetailBottom.animate().translationY(llDetailBottom.getHeight());
                } else if(scrollY - oldScrollY < 0 && !isBottomShow){    //上移出现
                    isBottomShow = true;
                    llDetailBottom.animate().translationY(0);
                }
            }
        });
        (getWindow().getSharedElementEnterTransition()).addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }
            @Override
            public void onTransitionEnd(Transition transition) {
                /**
                 * 测试发现部分手机(如红米note2)上加载图片会变形,没有达到centerCrop效果
                 * 查阅资料发现Glide配合SharedElementTransition是有坑的,需要在Transition动画结束后再加载图片
                 * https://github.com/TWiStErRob/glide-support/blob/master/src/glide3/java/com/bumptech/glide/supportapp/github/_847_shared_transition/DetailFragment.java
                 */
                isTransitionEnd = true;
                if (imgUrl != null) {
                    isImageShow = true;
                    ImageUtil.loadImg(mContext,detailBarImage,imgUrl);
                }
            }
            @Override
            public void onTransitionCancel(Transition transition) {
            }
            @Override
            public void onTransitionPause(Transition transition) {
            }
            @Override
            public void onTransitionResume(Transition transition) {
            }
        });
    }




    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }

    @Override
    public void showDetailData(ZhihuDetailBean zhihuDetailBean) {
        viewLoading.smoothToHide();
        imgUrl = zhihuDetailBean.getImage();
        shareUrl = zhihuDetailBean.getShare_url();
        if (!isImageShow && isTransitionEnd) {
            ImageUtil.loadImg(mContext,detailBarImage,zhihuDetailBean.getImage());
        }
        clpToolbar.setTitle(zhihuDetailBean.getTitle());
        detailBarCopyright.setText(zhihuDetailBean.getImage_source());
        String htmlData = HtmlUtil.createHtmlData(zhihuDetailBean.getBody(),zhihuDetailBean.getCss(),zhihuDetailBean.getJs());
        wvDetailContent.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }

    @Override
    public void showExtraData(DetailExtraBean detailExtraBean) {
        viewLoading.smoothToHide();
        tvDetailBottomLike.setText(String.format("%d个赞",detailExtraBean.getPopularity()));
        tvDetailBottomComment.setText(String.format("%d条评论",detailExtraBean.getComments()));
        allNum = detailExtraBean.getComments();
        shortNum = detailExtraBean.getShort_comments();
        longNum = detailExtraBean.getLong_comments();
    }
}
