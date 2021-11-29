package com.gantiexia.login.mapper;

import com.gantiexia.login.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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
     * 校验密码
     *
     * @param idNumber
     * @return
     */
    User getLoginUser(String idNumber);

    /**
     * 个人中心查询信息
     *
     * @param idNumber
     * @return
     */
    User getPersonInfo(String idNumber);

    /**
     * 个人信息页面修改头像
     * @param user
     * @return
     */
    int updatePersonPhoto(User user);

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    int updatePersonInfo(User user);
}
