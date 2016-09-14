package com.sujian.lines.view.layout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.sujian.lines.base.util.ThreadManagerLP;

/**
 * Created by sujian on 2016/9/14.
 * Mail:121116111@qq.com
 */
public abstract class LoadingPage extends FrameLayout{
    /**
     * 默认状态
     */
    private final int STATE_DEFAULt = 1;
    /**
     * 加载状态
     */
    private final int STATE_LONDING = 2;
    /**
     * 错误状态
     */
    private final int STATE_ERROR = 3;
    /**
     * 空状态
     */
    private final int STATE_EMPTY = 4;
    /**
     * 成功状态
     */
    public final static int STATE_SUCCESS = 5;

    /**
     * 记录当前状态
     */
    public int mState;

    /**
     * 加载中的View
     */
    private View mLoadingView;
    /**
     * 错误的View
     */
    private View mErrorView;
    /**
     * 空的View
     */
    private View mEmptyView;
    /**
     * 成功的View
     */
    private View mSuccessView;
    private Context mContext;

    public LoadingPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadingPage(Context context) {
        super(context);
        init(context);
    }

    /**
     * 初始化Page
     */
    private void init(Context context) {
        this.mContext = context;
        mState = STATE_DEFAULt;
        if (mLoadingView == null) {
            mLoadingView = createLoadingView();
            addView(mLoadingView, new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
        }
        if (mErrorView == null) {
            mErrorView = createErrorView();
            addView(mErrorView, new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
        }
        if (mEmptyView == null) {
            mEmptyView = createEmptyView();
            addView(mEmptyView, new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
        }

        showSafePage();
    }

    /**
     * 安全显示当前状态对应的页面
     */
    private void showSafePage() {
        mLoadingView.post(new Runnable() {

            @Override
            public void run() {
                showPage();
            }

        });
    }

    /**
     * 显示当前状态对应的页面
     */
    private void showPage() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(mState == STATE_DEFAULt
                    || mState == STATE_LONDING ? View.VISIBLE : View.INVISIBLE);
        }
        if (mErrorView != null) {
            mErrorView.setVisibility(mState == STATE_ERROR ? View.VISIBLE
                    : View.INVISIBLE);
        }
        if (mEmptyView != null) {
            mEmptyView.setVisibility(mState == STATE_EMPTY ? View.VISIBLE
                    : View.INVISIBLE);
        }

        if (mState == STATE_SUCCESS && mSuccessView == null) {
            mSuccessView = createSuccessView();
            addView(mSuccessView, new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
        }

        if (mSuccessView != null) {
            mSuccessView.setVisibility(mState == STATE_SUCCESS ? View.VISIBLE
                    : View.INVISIBLE);
        }
    }

    /**
     * 创建加载中的View
     */
    private View createLoadingView() {
        View view = LayoutInflater.from(mContext).inflate(getResourceIdByName("layout", "loading_page_loading"), null);
        return view;
    }

    /**
     * 创建加载失败的View
     */
    private View createErrorView() {
        View view = LayoutInflater.from(mContext).inflate(getResourceIdByName("layout", "loading_page_error"), null);
        view.findViewById(getResourceIdByName("id", "ll_content")).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        show();
                    }
                });
        return view;
    }

    /**
     * 创建加载成功但数据为空的View
     */
    private View createEmptyView() {
        return LayoutInflater.from(mContext).inflate(getResourceIdByName("layout", "loading_page_empty"), null);
    }

    /**
     * 创建成功状态的View
     */
    public abstract View createSuccessView();

    /**
     * 加载数据
     */
    public abstract LoadResult load();

    /**
     * 显示页面
     */
    public synchronized void show() {
        if (mState == STATE_ERROR || mState == STATE_EMPTY) {
            mState = STATE_DEFAULt;
        }
        if (mState == STATE_DEFAULt) {
            mState = STATE_LONDING;
            LoadingTask task = new LoadingTask();
            ThreadManagerLP.getLongPool().execute(task);
        }
        showSafePage();
    }

    /**
     * 重新显示页面
     */
    public synchronized void reShow() {
        if (mState == STATE_ERROR || mState == STATE_EMPTY
                || mState == STATE_SUCCESS) {
            mState = STATE_DEFAULt;
        }
        if (mSuccessView != null) {
            removeView(mSuccessView);
        }
        mSuccessView = null;
        if (mState == STATE_DEFAULt) {
            mState = STATE_LONDING;
            LoadingTask task = new LoadingTask();
            ThreadManagerLP.getLongPool().execute(task);
        }
        showSafePage();
    }

    private class LoadingTask implements Runnable {

        @Override
        public void run() {
            final LoadResult mLoadResult = load();
            if (mLoadResult != null) {
                mState = mLoadResult.getValue();
            }
            showSafePage();
        }

    }

    /**
     * 加载数据的结果
     */
    public enum LoadResult {
        ERROR(3), EMPTY(4), SUCCESS(5);
        /**
         * 3为数据错误4为没有数据5为加载数据成功
         */
        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 获取资源id
     *
     * @param className 资源类名称如（layout,drawable,mipmap,id）
     * @param name      资源名称如（tab_layout.xml 名称为tab_layout ）
     * @return 资源id
     */
    private int getResourceIdByName(String className, String name) {
        int id = 0;
        if (mContext == null) {
            return id;
        }
        String packageName = mContext.getPackageName();
        try {
            String e = packageName + ".R$" + className;
            Class desireClass = Class.forName(e);
            if (desireClass != null) {
                id = desireClass.getField(name).getInt(desireClass);
            }
        } catch (ClassNotFoundException var6) {
            Log.d("dou361", "ClassNotFoundException: class=" + className + " fieldname=" + name);
        } catch (IllegalArgumentException var7) {
            Log.d("dou361", "IllegalArgumentException: class=" + className + " fieldname=" + name);
        } catch (SecurityException var8) {
            Log.d("dou361", "SecurityException: class=" + className + " fieldname=" + name);
        } catch (IllegalAccessException var9) {
            Log.d("dou361", "IllegalAccessException: class=" + className + " fieldname=" + name);
        } catch (NoSuchFieldException var10) {
            Log.d("dou361", "NoSuchFieldException: class=" + className + " fieldname=" + name);
        }

        return id;
    }

}
