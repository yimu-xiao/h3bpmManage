package com.gstz.entity;

import java.io.Serializable;

/**
 * 对接系统表(AppInfo)实体类
 *
 * @author makejava
 * @since 2023-07-31 14:11:47
 */
public class AppInfo implements Serializable {
    private static final long serialVersionUID = 171452810727284546L;
    /**
     * 应用编号
     */
    private Integer appId;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 随机密钥
     */
    private String appKey;
    /**
     * ip白名单
     */
    private String appIp;
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

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppIp() {
        return appIp;
    }

    public void setAppIp(String appIp) {
        this.appIp = appIp;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

}

