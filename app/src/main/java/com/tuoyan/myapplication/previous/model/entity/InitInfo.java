package com.tuoyan.myapplication.previous.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */
public class InitInfo {
    private int apkversion;//版本
    private String iOSVersion;//版本
    private String apkurl;//下载URL
    private String limitTime;//限时
    private int wrongOpen; //推荐错题开关 0关闭 1开启
    private String activationOpen;//应该是是否激活了图书
    private String cychURL; //常用词汇前缀
    private String pmqcURL; //屏幕取词前缀
    private String phone; //客服电话
    private String shareUrl; //shareUrl
    private String activationDes; //激活说明URL
    private String listenURL; //
    private List<LevelType> sectionList;

    private String wxpayURL;//微信支付回调url
    private String alipayURL;//阿里支付回调url

    public class LevelType {
        private String value;//学段值（存储用）
        private String name;//学段名称

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public String getAlipayURL() {
        return alipayURL;
    }

    public String getiOSVersion() {
        return iOSVersion;
    }

    public void setiOSVersion(String iOSVersion) {
        this.iOSVersion = iOSVersion;
    }

    public void setAlipayURL(String alipayURL) {
        this.alipayURL = alipayURL;
    }

    public String getWxpayURL() {
        return wxpayURL;
    }

    public String getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(String limitTime) {
        this.limitTime = limitTime;
    }

    public void setWxpayURL(String wxpayURL) {
        this.wxpayURL = wxpayURL;
    }

    public String getApkurl() {
        return apkurl;
    }

    public void setApkurl(String apkurl) {
        this.apkurl = apkurl;
    }

    public int getApkversion() {
        return apkversion;
    }

    public void setApkversion(int apkversion) {
        this.apkversion = apkversion;
    }

    public List<LevelType> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<LevelType> sectionList) {
        this.sectionList = sectionList;
    }

    public int getWrongOpen() {
        return wrongOpen;
    }

    public void setWrongOpen(int wrongOpen) {
        this.wrongOpen = wrongOpen;
    }

    public String getCychURL() {
        return cychURL;
    }

    public void setCychURL(String cychURL) {
        this.cychURL = cychURL;
    }

    public String getPmqcURL() {
        return pmqcURL;
    }

    public void setPmqcURL(String pmqcURL) {
        this.pmqcURL = pmqcURL;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getActivationDes() {
        return activationDes;
    }

    public void setActivationDes(String activationDes) {
        this.activationDes = activationDes;
    }

    public String getActivationOpen() {
        return activationOpen;
    }

    public void setActivationOpen(String activationOpen) {
        this.activationOpen = activationOpen;
    }

    public String getListenURL() {
        return listenURL;
    }

    public void setListenURL(String listenURL) {
        this.listenURL = listenURL;
    }
}
