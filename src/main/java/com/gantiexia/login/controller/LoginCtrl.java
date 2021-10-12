package com.gantiexia.login.controller;

import com.gantiexia.login.entity.User;
import com.gantiexia.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
