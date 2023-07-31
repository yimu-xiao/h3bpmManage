package com.gstz.entity;

import java.io.Serializable;

/**
 * 请求模板表(HttpTemplate)实体类
 *
 * @author makejava
 * @since 2023-07-31 14:11:49
 */
public class HttpTemplate implements Serializable {
    private static final long serialVersionUID = 495377516613575978L;
    /**
     * 模板编号
     */
    private Integer templateId;
    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 模板类型（json/url参数拼接）
     */
    private String templateType;
    /**
     * 请求方式（POST/GET）
     */
    private String requestType;
    /**
     * 请求Json模板
     */
    private String jsonTemplate;
    /**
     * url模板
     */
    private String urlTemplate;
    /**
     * 排序号
     */
    private Integer sortNo;
    /**
     * 备注
     */
    private String remark;


    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getJsonTemplate() {
        return jsonTemplate;
    }

    public void setJsonTemplate(String jsonTemplate) {
        this.jsonTemplate = jsonTemplate;
    }

    public String getUrlTemplate() {
        return urlTemplate;
    }

    public void setUrlTemplate(String urlTemplate) {
        this.urlTemplate = urlTemplate;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}

