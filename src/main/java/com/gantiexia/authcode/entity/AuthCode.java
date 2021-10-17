package com.gantiexia.authcode.entity;

import java.util.Date;

/**
 * 验证码实体集
 *
 * @author GanTieXia
 * @date 2021/10/17 15:06
 */
public class AuthCode {
    /** 主键*/
    private String id;
    /** 账号*/
    private String idNumber;
    /** 验证码*/
    private String authCode;
    /** 创建时间*/
    private Date crateTime;

    /** email*/
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
