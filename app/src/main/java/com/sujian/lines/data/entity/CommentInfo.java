package com.sujian.lines.data.entity;


import com.sujian.lines.base.BaseEntity;

/**
 * 评论返回的数据信息
 */
public class CommentInfo extends BaseEntity.BaseBean {
    public String content;
    public _User creater;
    public String nid;
    public int num;
    public String  createdAt;
}
