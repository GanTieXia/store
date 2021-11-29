package com.gantiexia.personal.service;

import com.gantiexia.login.entity.User;

import java.util.Map;

/**
 * @author GanTieXia
 * @date 2021/11/29 14:19
 */
public interface PersonalService {

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    Map<String,String> updatePersonInfo(User user);
}
