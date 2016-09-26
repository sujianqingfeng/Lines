package com.sujian.lines.data.entity;

import com.sujian.lines.base.BaseEntity;
import com.sujian.lines.data.Pointer;

/**
 * 收藏的实体类
 * Created by sujian on 2016/9/26.
 * Mail:121116111@qq.com
 */

public class Like extends BaseEntity.BaseBean{
    private String lid;
    private String image;
    private String title;
    private int type;
    private long time;
    private Pointer create;

    public Pointer getCreate() {
        return create;
    }

    public void setCreate(Pointer create) {
        this.create = create;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String id) {
        this.lid = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id='" + lid + '\'' +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", time=" + time +
                '}';
    }
}
