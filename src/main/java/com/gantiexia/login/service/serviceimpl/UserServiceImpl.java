package com.gantiexia.login.service.serviceimpl;

import com.gantiexia.login.entity.User;
import com.gantiexia.login.mapper.LoginMapper;
import com.gantiexia.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GanTieXia
 * @date 2021/10/7 0:55
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private LoginMapper loginMapper;

    /** 存储账号*/
    private String idNumber;
    /** 存储用户名*/
    private String userName;

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public Map<String,String> getUserMessage(User user) {
        // 查询出系统此账号的账号信息
        User userSys = loginMapper.getUserMessage(user);

        // 设置账号信息用于前端获取显示
        idNumber = userSys.getIdNumber();
        userName = userSys.getUserName();

        // 创建一个Map集合装处理结果
        Map map = new HashMap(2);
        // 如果系统中的密码与输入的密码不相等
        if(userSys != null){
            if(userSys.getPassword().equals(user.getPassword())){
                map.put("code","200");
                map.put("message","登陆成功...");
            } else if (!userSys.getPassword().equals(user.getPassword())){
                map.put("code","404");
                map.put("message","登陆失败...");
            }
        } else {
            map.put("code","404");
            map.put("message","登陆失败...");
        }

        return map;
    }

    /**
     * 前端回显账号信息
     *
     * @return
     */
    @Override
    public User showUserMessage() {
        User user = new User();
        // 设置user的回显字段
        user.setIdNumber(idNumber);
        user.setUserName(userName);
        return user;
    }
}
