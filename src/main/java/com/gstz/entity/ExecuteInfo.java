package com.gstz.entity;

import java.io.Serializable;

/**
 * 执行系统表(ExecuteInfo)实体类
 *
 * @author makejava
 * @since 2023-07-31 14:12:15
 */
public class ExecuteInfo implements Serializable {
    private static final long serialVersionUID = 956240696959228198L;
    /**
     * 执行编号
     */
    private Integer executeId;
    /**
     * 模板编号
     */
    private Integer templateId;
    /**
     * 应用编号
     */
    private Integer appId;


    public Integer getExecuteId() {
        return executeId;
    }

    public void setExecuteId(Integer executeId) {
        this.executeId = executeId;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

}

