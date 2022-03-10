package com.gantiexia.userManage.controller;

import com.gantiexia.authcode.entity.AuthCode;
import com.gantiexia.userManage.entity.User;
import com.gantiexia.userManage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
     * 登陆
     *
     * @return
     */
    @RequestMapping("/loginSystem")
    @ResponseBody
    public Map<String,String> loginSystem(User user, HttpServletRequest request){
        return userService.getUserMessage(user,request);
    }

    /**
     * 返回登陆人信息
     *
     * @return
     */
    @RequestMapping("/showUserMessage")
    @ResponseBody
    public User getLoginUser(){
        String idNumber = userService.showUserMessage();
        return userService.getPersonInfo(idNumber);
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

    /**
     * 用户数据
     *
     * @return
     */
    @RequestMapping("/userMessageList")
    @ResponseBody
    public Map<String,Object> userMessageList(User user,@RequestParam int page,@RequestParam int limit){
        return userService.getUserMessageList(user,page,limit);
    }

    /**
     * 删除用户
     *
     * @return
     */
    @RequestMapping("/delUser")
    @ResponseBody
    public Map<String,String> delUser(User user){
        return userService.delUser(user);
    }

    /**
     * 禁用用户
     *
     * @return
     */
    @RequestMapping("/updateIsOnUse")
    @ResponseBody
    public Map<String,String> updateIsOnUse(User user){
        return userService.updateIsOnUse(user);
    }

    /**
     * 解除禁用
     *
     * @return
     */
    @RequestMapping("/updateOnUse")
    @ResponseBody
    public Map<String,String> updateOnUse(User user){
        return userService.updateOnUse(user);
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping("/updatePassword")
    @ResponseBody
    public Map<String,String> updatePassword(User user){
        return userService.editPassWord(user);
    }
}
