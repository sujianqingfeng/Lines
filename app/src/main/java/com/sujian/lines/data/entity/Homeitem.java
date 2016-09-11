package com.sujian.lines.data.entity;

import com.sujian.lines.base.BaseEntity;

/**
 * Created by sujian on 2016/9/11.
 * Mail:121116111@qq.com
 */
public class Homeitem extends BaseEntity.BaseBean{
    private String title;
    private String date;
    private String sources;
    private String url_img;
    private String content;
    private String desc;
    private String nid;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    @Override
    public String toString() {
        return "Homeitem{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", sources='" + sources + '\'' +
                ", url_img='" + url_img + '\'' +
                '}';
    }
}
