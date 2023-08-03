package com.gstz.entity;

import java.io.Serializable;

/**
 * 请求模板表(HttpTemplate)实体类
 *
 * @author makejava
 * @since 2023-07-31 17:13:58
 */
public class HttpTemplate implements Serializable {
    private static final long serialVersionUID = -48881180635539542L;
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
     * 参数来源 （null:固定配置值，从码表取，否则为请求获取参数存在临时表存值为请求的模板编号）
     */
    private Integer paramId;
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

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
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

