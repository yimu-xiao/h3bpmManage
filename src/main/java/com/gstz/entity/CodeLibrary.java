package com.gstz.entity;

import java.io.Serializable;

/**
 * 码值表(CodeLibrary)实体类
 *
 * @author makejava
 * @since 2023-07-28 11:12:42
 */
public class CodeLibrary implements Serializable {
    private static final long serialVersionUID = 441494729193660763L;
    /**
     * 码值编号
     */
    private String codeNo;
    /**
     * 转码编号
     */
    private String itemNo;
    /**
     * 转码值
     */
    private String itemName;
    /**
     * 参数
     */
    private String itemParam;
    /**
     * 排序号
     */
    private Integer sortNo;

    public CodeLibrary(String codeNo) {
        this.codeNo = codeNo;
    }

    public String getCodeNo() {
        return codeNo;
    }

    public void setCodeNo(String codeNo) {
        this.codeNo = codeNo;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemParam() {
        return itemParam;
    }

    public void setItemParam(String itemParam) {
        this.itemParam = itemParam;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

}

