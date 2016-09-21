package com.sujian.lines.ui.zhihu.section.activity;

import com.sujian.lines.data.entity.SectionChildListBean;

import rx.Subscriber;

/**
 * Created by sujian on 2016/9/19.
 * Mail:121116111@qq.com
 */
public class SectionAPresenter extends SectionAContract.Presenter {


    @Override
    public void onStart() {

    }

    @Override
    void getSectionData(int id) {
        mRxManager.add(mModel.getSectionData(id)
        .subscribe(new Subscriber<SectionChildListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SectionChildListBean sectionChildListBean) {
                mView.showSection(sectionChildListBean);
            }
        })
        );
    }
}
