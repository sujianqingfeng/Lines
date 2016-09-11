package com.sujian.lines.data;


import com.sujian.lines.base.BaseRepository;

import java.util.Map;

import rx.Observable;


public abstract class Repository<T> extends BaseRepository {
    public T data;

    public Map<String, String> param;

    public abstract Observable<Data<T>> getPageAt(int page);
}
