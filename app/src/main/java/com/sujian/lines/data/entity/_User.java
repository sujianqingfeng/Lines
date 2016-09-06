package com.sujian.lines.data.entity;


import com.sujian.lines.base.BaseEntity;

/**
 * 用户信息
 */
public class _User extends BaseEntity.BaseBean {

    private String username;
    private String password;
    private String face;
    private String sessionToken;

    public _User() {
    }

    public _User(String name, String pass) {
        this.username = name;
        this.password = pass;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
