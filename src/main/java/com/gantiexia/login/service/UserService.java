package com.gantiexia.login.service;

import com.gantiexia.authcode.entity.AuthCode;
import com.gantiexia.login.entity.User;
import org.springframework.web.multipart.MultipartFile;

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
}
