package com.sujian.lines.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sujian.lines.base.util.TUtil;

import butterknife.ButterKnife;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment {

    public T mPresenter;
    public E mModel;
    public Context mContext;


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

        if (this instanceof BaseView) mPresenter.setVM(this, mModel);

        initView();


    }



    protected abstract void initView();

    public abstract int getLayoutId();
}
