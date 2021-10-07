package com.gantiexia.login.service;

import com.gantiexia.login.entity.User;

import java.util.Map;

/**
 * @author GanTieXia
 * @date 2021/10/7 0:55
 */
public interface UserService {

    /**
     * 核对登录密码
     *
     * @param user
     * @return
     */
    Map<String,String> getUserMessage(User user);

    /**
     * 前端回显账号信息
     *
     * @return
     */
    User showUserMessage();
}
