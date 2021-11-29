package com.gantiexia.personal.service.personalImpl;

import com.gantiexia.login.entity.User;
import com.gantiexia.login.mapper.LoginMapper;
import com.gantiexia.personal.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GanTieXia
 * @date 2021/11/29 14:20
 */
@Service
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    private LoginMapper loginMapper;

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    @Override
    public Map<String,String> updatePersonInfo(User user) {
        int n = loginMapper.updatePersonInfo(user);
        Map<String,String> map = new HashMap<>();
        if(n == 1){
            map.put("code","200");
            return  map;
        } else {
            map.put("code","404");
            return  map;
        }
    }
}
