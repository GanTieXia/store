package com.gantiexia.personal.controller;

import com.gantiexia.authcode.entity.AuthCode;
import com.gantiexia.login.entity.User;
import com.gantiexia.personal.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author GanTieXia
 * @date 2021/11/29 14:18
 */
@Controller
@RequestMapping("person")
public class PersonalCtrl {

    @Autowired
    private PersonalService personalService;

    /**
     * 修改个人信息
     *
     * @return
     */
    @RequestMapping("/updatePersonInfo")
    @ResponseBody
    public Map<String,String> updatePersonInfo(@RequestBody Map<String,String> reqMap){
        User user = new User();
        user.setUserName(reqMap.get("userName"));
        user.setIdNumber(reqMap.get("idNumber"));
        user.setPersonBriefly(reqMap.get("personBriefly"));
        user.setEmail(reqMap.get("email"));
        user.setSex(reqMap.get("sex"));
        return personalService.updatePersonInfo(user);
    }
}
