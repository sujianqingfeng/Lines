package com.sujian.lines.data.entity;

import com.sujian.lines.data.Pointer;

/**
 * Created by sujian on 2016/9/26.
 * Mail:121116111@qq.com
 */

public class LikeQuery {
    private Pointer create;
    private String lid;

    public Pointer getCreate() {
        return create;
    }

    public void setCreate(Pointer create) {
        this.create = create;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    @Override
    public String toString() {
        return "LikeQuery{" +
                "create=" + create +
                ", lid='" + lid + '\'' +
                '}';
    }
}
