package com.sujian.lines.data.entity;


import com.sujian.lines.base.BaseEntity;
import com.sujian.lines.data.Pointer;

/**
 * 评论
 */
public class Comment extends BaseEntity.BaseBean {

    public String nid;
    public int num;
    public String content;
    public Pointer creater;

    public Comment(String nid, String content, Pointer creater) {
        this.nid=nid;
        this.content = content;
        this.creater = creater;
    }

    public Comment(String nid) {
        this.nid = nid;
    }
}
