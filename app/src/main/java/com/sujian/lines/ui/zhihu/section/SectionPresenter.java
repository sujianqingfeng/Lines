package com.sujian.lines.ui.zhihu.section;

import com.sujian.lines.data.entity.SectionListBean;

import rx.Subscriber;

/**
 * Created by sujian on 2016/9/13.
 * Mail:121116111@qq.com
 */
public class SectionPresenter extends SectionContract.Presenter {
    @Override
    void getSection() {
        mRxManager.add(mModel.getSectionList()
        .subscribe(new Subscriber<SectionListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SectionListBean sectionListBean) {
                mView.showSection(sectionListBean);
            }
        })
        );
    }

    @Override
    public void onStart() {
getSection();
    }
}
