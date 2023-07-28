package com.gstz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 用户表(UserInfo)实体类
 *
 * @author makejava
 * @since 2022-12-07 10:01:30
 */
@TableName
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 252345749924528525L;
    /**
     * 用户中心登录名
     */
    @TableId
    private String uid;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户编码（唯一标识）
     */
    private String personid;
    /**
     * 员工号
     */
    private String employeecode;
    /**
     * 用户中心组织编码
     */
    private String orgcode;
    /**
     * 用户中心组织名称
     */
    private String orgname;
    /**
     * 状态 0-停用, 1-启用, 2-锁定
     */
    private String status;
    /**
     * 全拼
     */
    private String pinyin;
    /**
     * 性别
     */
    private String sex;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户类型
     */
    private String usertype;
    /**
     * 座机
     */
    private String phone;
    /**
     * 排序
     */
    @TableField(exist = false)
    private Integer order;
    /**
     * 排序
     */
    private Integer orders;
    /**
     * 入职日期
     */
    private String begindate;
    /**
     * 有效期
     */
    private String validate;
    /**
     * 工位
     */
    private String workloc;
    /**
     * 企业微信userid
     */
    private String wecharid;
    /**
     * 职级
     */
    private String jobrank;
    /**
     * 房号
     */
    private String userroom;
    /**
     * 直拨号
     */
    private String directphone;
    /**
     * 所在部门主要职务编码
     */
    private String mainjobcode;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifytime;
    /**
     * 扩展字段1
     */
    private String ext1;
    /**
     * 扩展字段2
     */
    private String ext2;
    /**
     * 扩展字段3
     */
    private String ext3;
    /**
     * 扩展字段4
     */
    private String ext4;
    /**
     * 扩展字段5
     */
    private String ext5;
    /**
     * 角色(admin、editor)
     */
    private String role;
    private String img;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(uid, userInfo.uid) && Objects.equals(name, userInfo.name) && Objects.equals(personid, userInfo.personid) && Objects.equals(employeecode, userInfo.employeecode) && Objects.equals(orgcode, userInfo.orgcode) && Objects.equals(orgname, userInfo.orgname) && Objects.equals(status, userInfo.status) && Objects.equals(pinyin, userInfo.pinyin) && Objects.equals(sex, userInfo.sex) && Objects.equals(email, userInfo.email) && Objects.equals(usertype, userInfo.usertype) && Objects.equals(phone, userInfo.phone) && Objects.equals(orders, userInfo.orders) && Objects.equals(begindate, userInfo.begindate) && Objects.equals(validate, userInfo.validate) && Objects.equals(workloc, userInfo.workloc) && Objects.equals(wecharid, userInfo.wecharid) && Objects.equals(jobrank, userInfo.jobrank) && Objects.equals(userroom, userInfo.userroom) && Objects.equals(directphone, userInfo.directphone) && Objects.equals(mainjobcode, userInfo.mainjobcode) && Objects.equals(createtime, userInfo.createtime) && Objects.equals(modifytime, userInfo.modifytime) && Objects.equals(ext1, userInfo.ext1) && Objects.equals(ext2, userInfo.ext2) && Objects.equals(ext3, userInfo.ext3) && Objects.equals(ext4, userInfo.ext4) && Objects.equals(ext5, userInfo.ext5) && Objects.equals(role, userInfo.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, name, personid, employeecode, orgcode, orgname, status, pinyin, sex, email, usertype, phone, orders, begindate, validate, workloc, wecharid, jobrank, userroom, directphone, mainjobcode, createtime, modifytime, ext1, ext2, ext3, ext4, ext5, role);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", personid='" + personid + '\'' +
                ", employeecode='" + employeecode + '\'' +
                ", orgcode='" + orgcode + '\'' +
                ", orgname='" + orgname + '\'' +
                ", status='" + status + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", usertype='" + usertype + '\'' +
                ", phone='" + phone + '\'' +
                ", order=" + order +
                ", orders=" + orders +
                ", begindate='" + begindate + '\'' +
                ", validate='" + validate + '\'' +
                ", workloc='" + workloc + '\'' +
                ", wecharid='" + wecharid + '\'' +
                ", jobrank='" + jobrank + '\'' +
                ", userroom='" + userroom + '\'' +
                ", directphone='" + directphone + '\'' +
                ", mainjobcode='" + mainjobcode + '\'' +
                ", createtime=" + createtime +
                ", modifytime=" + modifytime +
                ", ext1='" + ext1 + '\'' +
                ", ext2='" + ext2 + '\'' +
                ", ext3='" + ext3 + '\'' +
                ", ext4='" + ext4 + '\'' +
                ", ext5='" + ext5 + '\'' +
                ", role='" + role + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}

