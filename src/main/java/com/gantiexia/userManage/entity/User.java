package com.gantiexia.userManage.entity;

import java.util.Date;

/**
 * @author GanTieXia
 * @date 2021/10/7 0:46
 */
public class User {
    /** 主键*/
    private String id;
    /** 用户名*/
    private String userName;
    /** 账号*/
    private String idNumber;
    /** 密码*/
    private String password;
    /** 邮箱*/
    private String email;
    /** 性别*/
    private String sex;
    /** 头像路径*/
    private String personagePicture;
    /** 创建时间*/
    private Date createTime;
    private String createTimeText;
    /** 个人简介*/
    private String personBriefly;
    /** 是否禁用*/
    private String isOnUse;

    /** 验证码*/
    private String authCode;

    public String getCreateTimeText() {
        return createTimeText;
    }

    public void setCreateTimeText(String createTimeText) {
        this.createTimeText = createTimeText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPersonagePicture() {
        return personagePicture;
    }

    public void setPersonagePicture(String personagePicture) {
        this.personagePicture = personagePicture;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getPersonBriefly() {
        return personBriefly;
    }

    public void setPersonBriefly(String personBriefly) {
        this.personBriefly = personBriefly;
    }

    public String getIsOnUse() {
        return isOnUse;
    }

    public void setIsOnUse(String isOnUse) {
        this.isOnUse = isOnUse;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", personagePicture='" + personagePicture + '\'' +
                ", createTime=" + createTime +
                ", createTimeText='" + createTimeText + '\'' +
                ", personBriefly='" + personBriefly + '\'' +
                ", isOnUse='" + isOnUse + '\'' +
                ", authCode='" + authCode + '\'' +
                '}';
    }
}
