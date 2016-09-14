package com.sujian.lines.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sujian.lines.base.util.TUtil;
import com.sujian.lines.base.util.ViewUtil;
import com.sujian.lines.view.layout.LoadingPage;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends SupportFragment {

    public T mPresenter;
    public E mModel;
    public Context mContext;
    private boolean isInited = false;

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return View.inflate(getActivity(),getLayoutId(),null);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);

        if (savedInstanceState == null) {
            if (!isHidden()) {
                isInited = true;
               initView();
            }
        } else {
            if (!isSupportHidden()) {
                isInited = true;
                initView();
            }
        }

        if (this instanceof BaseView) mPresenter.setVM(this, mModel);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isInited && !hidden) {
            isInited = true;
            initView();
        }
    }


    protected abstract void initView();

    public abstract int getLayoutId();
}
