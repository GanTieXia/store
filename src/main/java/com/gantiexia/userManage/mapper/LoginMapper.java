package com.gantiexia.userManage.mapper;

import com.gantiexia.userManage.entity.User;
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
     * 查询用户表
     *
     * @param map
     * @return
     */
    List<User> getUserMessageList(Map<String,Object> map);

    /**
     * 查询用户表数量
     * @param map
     * @return
     */
    int getUserMessageListCount(Map<String,Object> map);

    /**
     * 填充注册账号输入框
     *
     * @return
     */
    String getNextIdNumber();

    /**
     * 删除用户
     * @param id
     * @return
     */
    int delUser(String id);

    /**
     * 禁用用户
     *
     * @param id
     * @return
     */
    int updateIsOnUse(String id);

    /**
     * 解除禁用
     *
     * @param id
     * @return
     */
    int updateOnUse(String id);

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

    /**
     * 通过邮箱查询出此人的账号
     *
     * @param email
     * @return
     */
    String getIdNumber(String email);

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    int updatePassword(User user);
}
