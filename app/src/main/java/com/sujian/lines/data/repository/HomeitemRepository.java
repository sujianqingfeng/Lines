package com.sujian.lines.data.repository;

import android.util.Log;

import com.sujian.lines.api.NewApi;
import com.sujian.lines.base.BaseRepository;
import com.sujian.lines.base.util.ApiUtil;

import com.sujian.lines.base.util.helper.RxSchedulers;
import com.sujian.lines.data.Data;
import com.sujian.lines.data.Repository;
import com.sujian.lines.data.entity.Homeitem;
import com.sujian.lines.data.entity.HomeitemInfo;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by sujian on 2016/9/11.
 * Mail:121116111@qq.com
 */
public class HomeitemRepository extends Repository<Homeitem> {
    @Override
    public Observable<Data<Homeitem>> getPageAt(int page) {
        Log.e("leixin",param.get("type"));

        return NewApi.getInstance()
                .service
                .getHomeItemInfo(param.get("type"),page)
             .flatMap(new Func1<HomeitemInfo, Observable<Data<Homeitem>>>() {
                 @Override
                 public Observable<Data<Homeitem>> call(HomeitemInfo homeitemInfo) {
                     Log.e("-------信息",homeitemInfo.toString());
                     Data<Homeitem> list = new Data<Homeitem>();
                     list.results=new ArrayList<Homeitem>();
                     List<HomeitemInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist
                             = homeitemInfo.getShowapi_res_body().getPagebean().getContentlist();
                     Homeitem homeItem;

                     for (HomeitemInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean s:contentlist) {
                         homeItem=new Homeitem();
                         homeItem.setTitle(s.getTitle());
                         homeItem.setDesc(s.getDesc());
                         homeItem.setSources(s.getSource());
                         homeItem.setDate(s.getPubDate());
                         homeItem.setContent(s.getContent());
                         homeItem.setNid(s.getNid());
                         if (s.getImageurls().size()>0)
                         homeItem.setUrl_img(s.getImageurls().get(0).getUrl());
                         list.results.add(homeItem);
                     }
                     return Observable.just(list);
                 }
             })
                .compose(RxSchedulers.io_main());

    }
}
