package com.gantiexia.login.entity;

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
}
