package com.gantiexia.login.mapper;

import com.gantiexia.login.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author GanTieXia
 * @date 2021/10/7 0:45
 */
@Mapper
public interface LoginMapper {
    /**
     * 核对登录密码
     * @param user
     * @return
     */
    List<User> getUserMessage(User user);

    /**
     * 填充注册账号输入框
     *
     * @return
     */
    String getNextIdNumber();

    /**
     * 注册
     *
     * @param user
     * @return
     */
    int register(User user);

    /**
     * 获取当前登录用户
     *
     * @param idNumber
     * @return
     */
    User getLoginUser(String idNumber);
}
