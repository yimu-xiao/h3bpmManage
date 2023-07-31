package com.gstz.entity;

import java.io.Serializable;

/**
 * 对接系统表(AppInfo)实体类
 *
 * @author makejava
 * @since 2023-07-31 11:21:30
 */
public class AppInfo implements Serializable {
    private static final long serialVersionUID = 486453997369412767L;
    /**
     * 应用编号
     */
    private Integer appId;
    /**
     * 执行编号
     */
    private Integer executeId;
    /**
     * 随机密钥
     */
    private String appKey;
    /**
     * ip白名单
     */
    private String appIp;
    /**
     * 应用名称
     */
    private String appName;
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

    public Integer getExecuteId() {
        return executeId;
    }

    public void setExecuteId(Integer executeId) {
        this.executeId = executeId;
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

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

}

