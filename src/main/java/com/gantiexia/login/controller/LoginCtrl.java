package com.gantiexia.login.controller;

import com.gantiexia.authcode.entity.AuthCode;
import com.gantiexia.login.entity.User;
import com.gantiexia.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author GanTieXia
 * @date 2021/10/6 2:30
 */
@Controller
@RequestMapping("login")
public class LoginCtrl {

    @Autowired
    private UserService userService;

    /**
     * 返回登陆人信息
     *
     * @return
     */
    @RequestMapping("/loginSystem")
    @ResponseBody
    public Map<String,String> loginSystem(User user){
        return userService.getUserMessage(user);
    }

    /**
     * 返回登陆人信息
     *
     * @return
     */
    @RequestMapping("/showUserMessage")
    @ResponseBody
    public User showUserMessage(){
        return userService.showUserMessage();
    }

    /**
     * 填充注册界面的账号
     *
     * @return
     */
    @RequestMapping("/getNextIdNumber")
    @ResponseBody
    public String getNextIdNumber(){
        return userService.getNextIdNumber();
    }

    /**
     * 返回邮箱的验证码
     *
     * @return
     */
    @RequestMapping("/checkCode")
    @ResponseBody
    public Map<String,String> CheckCode(AuthCode authCode){
        return userService.checkCode(authCode);
    }

    /**
     * 头像图片上传功能
     *
     * @param multipartFile
     * @param user
     * @return
     */
    @RequestMapping("/layupload")
    @ResponseBody
    public Map<String,String> saveUploadPicture(@RequestParam(value="file") MultipartFile multipartFile, User user) {
        return userService.saveUploadPicture(multipartFile,user);
    }

    /**
     * 注册功能
     *
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public Map<String,String> register(User user){
        return userService.register(user);
    }
}
