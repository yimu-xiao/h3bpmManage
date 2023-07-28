package com.gstz.entity;

import java.io.Serializable;

/**
 * 对接系统表(AppInfo)实体类
 *
 * @author makejava
 * @since 2023-07-28 10:48:42
 */
public class AppInfo implements Serializable {
    private static final long serialVersionUID = -93706991023866266L;
    /**
     * 应用编号
     */
    private Integer appId;
    /**
     * 应用名称
     */
    private Integer appName;
    /**
     * 对接方式（token/key/head）
     */
    private String appType;


    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getAppName() {
        return appName;
    }

    public void setAppName(Integer appName) {
        this.appName = appName;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

}

