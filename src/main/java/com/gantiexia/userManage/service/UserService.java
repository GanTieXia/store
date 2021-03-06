package com.gantiexia.userManage.service;

import com.gantiexia.authcode.entity.AuthCode;
import com.gantiexia.userManage.entity.User;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
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
    Map<String,String> getUserMessage(User user, HttpServletRequest request);

    /**
     * 前端回显账号信息
     *
     * @return
     */
    String showUserMessage();

    /**
     * 获取当前登录用户校验密码
     *
     * @param idNumber
     * @return
     */
    User getLoginUser(String idNumber);

    /**
     * 个人中心信息
     *
     * @param idNumber
     * @return
     */
    User getPersonInfo(String idNumber);

    /**
     * 邮箱验证码
     *
     * @return
     */
    Map<String,String> checkCode(AuthCode authCode);

    /**
     * 填充注册账号输入框
     *
     * @return
     */
    String getNextIdNumber();

    /**
     * 头像上传功能
     *
     * @param multipartFile
     * @param user
     * @return
     */
    Map<String,String> saveUploadPicture(MultipartFile multipartFile, User user);

    /**
     * 注册功能
     *
     * @param user
     * @return
     */
    Map<String,String> register(User user);

    /**
     * 修改密码功能
     *
     * @param user
     * @return
     */
    Map<String,String> editPassWord(User user);

    /**
     * 核对登录密码
     *
     * @param user
     * @return
     */
    Map<String,Object> getUserMessageList(User user,int page,int limit);

    /**
     * 删除用户
     *
     * @param user
     * @return
     */
    Map<String,String> delUser(User user);

    /**
     * 禁用用户
     *
     * @param user
     * @return
     */
    Map<String,String> updateIsOnUse(User user);

    /**
     * 解除禁用
     *
     * @param user
     * @return
     */
    Map<String,String> updateOnUse(User user);
}
