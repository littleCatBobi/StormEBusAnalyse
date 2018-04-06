package com.itstaredu.entity;

import java.io.Serializable;
/**
 * 浏览器日志pojo类
 */
public class BrowserLog implements Serializable {

    private static final long serialVersionUID = 5595911808040763416L;
    private String type; //1.浏览日志 2.点击日志3.搜索日志4.购买日志
    private String hrefTag;  //标签标识
    private String hrefContent;  //标签对应的标识，主要针对a标签后的内容
    private String referrerUrl; //来源网址
    private String requestUrl; //来源网址
    private String clickTime;  //点击时间
    private String appName;  //浏览器类型
    private String appVersion; //浏览器版本
    private String language; //浏览器语言
    private String platform;  //操作系统
    private String screen;  //屏幕尺寸
    private String coordinate;  //点击鼠标时的坐标
    private String systemId;  //产生点击流的系统编号
    private String userName;  //用户名称
    private long timeStamp;//  时间戳

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHrefTag() {
        return hrefTag;
    }

    public void setHrefTag(String hrefTag) {
        this.hrefTag = hrefTag;
    }

    public String getHrefContent() {
        return hrefContent;
    }

    public void setHrefContent(String hrefContent) {
        this.hrefContent = hrefContent;
    }

    public String getReferrerUrl() {
        return referrerUrl;
    }

    public void setReferrerUrl(String referrerUrl) {
        this.referrerUrl = referrerUrl;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getClickTime() {
        return clickTime;
    }

    public void setClickTime(String clickTime) {
        this.clickTime = clickTime;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "BrowserLog{" +
                "type='" + type + '\'' +
                ", hrefTag='" + hrefTag + '\'' +
                ", hrefContent='" + hrefContent + '\'' +
                ", referrerUrl='" + referrerUrl + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", clickTime='" + clickTime + '\'' +
                ", appName='" + appName + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", language='" + language + '\'' +
                ", platform='" + platform + '\'' +
                ", screen='" + screen + '\'' +
                ", coordinate='" + coordinate + '\'' +
                ", systemId='" + systemId + '\'' +
                ", userName='" + userName + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
