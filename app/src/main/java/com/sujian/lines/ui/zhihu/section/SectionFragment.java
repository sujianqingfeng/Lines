package com.sujian.lines.ui.zhihu.section;

import com.sujian.lines.R;
import com.sujian.lines.base.BaseFragment;

/**
 * 专栏
 */
public class SectionFragment extends BaseFragment<SectionPresenter,SectionModel> implements SectionContract.View {

    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_section;
    }
}
