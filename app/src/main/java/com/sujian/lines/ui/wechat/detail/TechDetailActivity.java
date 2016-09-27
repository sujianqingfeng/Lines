package com.sujian.lines.ui.wechat.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.base.util.NetWorkUtil;
import com.sujian.lines.base.util.ShareUtil;
import com.sujian.lines.base.util.SpUtil;
import com.sujian.lines.base.util.SystemUtil;
import com.sujian.lines.base.util.ToastUtil;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.Bind;

public class TechDetailActivity extends BaseActivity<TechDetailPresenter,TechDetailModel> implements TechDetailContract.View {

    @Bind(R.id.tool_bar)
    Toolbar toolBar;
    @Bind(R.id.wv_tech_content)
    WebView wvTechContent;
    @Bind(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;

    MenuItem menuItem;

    String title,url,id,tech;
    boolean isLiked;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tech_detail;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        tech = intent.getExtras().getString("tech");
        title = intent.getExtras().getString("title");
        url = intent.getExtras().getString("url");
        id = intent.getExtras().getString("id");
        setToolBar(toolBar,title);
        WebSettings settings = wvTechContent.getSettings();
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
        wvTechContent.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wvTechContent.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    viewLoading.smoothToHide();
                } else {
                    viewLoading.smoothToShow();
                }
            }
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }
        });
        wvTechContent.loadUrl(url);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wvTechContent.canGoBack()) {
            wvTechContent.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tech_meun, menu);
        menuItem = menu.findItem(R.id.action_like);
        mPresenter.queryLikeData(id);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_like:
                if(isLiked) {
                    mPresenter.deleteLikeData();
                } else {
                    mPresenter.insertLikeData(getIntent());
                }
                break;
            case R.id.action_copy:
                SystemUtil.copyToClipBoard(mContext,url);
                return true;
            case R.id.action_share:
                ShareUtil.shareText(mContext,url,"分享一篇文章");
        }
        return super.onOptionsItemSelected(item);
    }

    public void setLikeState(boolean state) {
        if(state) {
            menuItem.setIcon(R.mipmap.ic_toolbar_like_p);
            isLiked = true;
        } else {
            menuItem.setIcon(R.mipmap.ic_toolbar_like_n);
            isLiked = false;
        }
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.show(msg);
    }
}
