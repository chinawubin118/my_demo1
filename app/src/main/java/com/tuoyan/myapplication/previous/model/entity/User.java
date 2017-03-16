package com.tuoyan.myapplication.previous.model.entity;

/**
 * Created by dph on 2016/5/25.
 */
public class User {
    private String id;
    private String icon;
    private String levelType;  //所处学段 四级、六级。。。
    private int sex;  //性别 0 男 1女
    private String nickName;
    private String mobile;
    private int fActivation; //四级是否激活 0否 1是
    private int sActivation; //六级是否激活 0否 1是
    private String wxOpenId;//string	微信OpenId
    private String qqOpenId;//string	qqOpenId
    private String username;//

    public String getWbOpenId() {
        return wbOpenId;
    }

    public void setWbOpenId(String wbOpenId) {
        this.wbOpenId = wbOpenId;
    }

    private String wbOpenId;//string	qqOpenId

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public int getfActivation() {
        return fActivation;
    }

    public void setfActivation(int fActivation) {
        this.fActivation = fActivation;
    }

    public int getsActivation() {
        return sActivation;
    }

    public void setsActivation(int sActivation) {
        this.sActivation = sActivation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
