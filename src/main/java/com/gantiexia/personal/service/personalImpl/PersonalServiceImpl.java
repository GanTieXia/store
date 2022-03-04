package com.gantiexia.personal.service.personalImpl;

import com.gantiexia.userManage.entity.User;
import com.gantiexia.userManage.mapper.LoginMapper;
import com.gantiexia.personal.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
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
        Map<String,String> map = new HashMap<>();

        // 如果改了邮箱，验证邮箱是否重复
        String userId = user.getIdNumber();
        User userRecord = loginMapper.getLoginUser(userId);

        User userPram = new User();
        List<User> userList = loginMapper.getUserMessage(userPram);

        // 如果修改了邮箱
        if(!userRecord.getEmail().equals(user.getEmail())){
            // 校验是否重复
            for(User u : userList){
                if(user.getEmail().equals(u.getEmail())){
                    map.put("code","505");
                    map.put("message","邮箱重复，修改失败！");
                    return  map;
                }
            }
        }

        // 执行修改
        int n = loginMapper.updatePersonInfo(user);

        if(n == 1){
            map.put("code","200");
            map.put("message","操作成功！");
            return  map;
        } else {
            map.put("code","404");
            map.put("message","操作失败！");
            return  map;
        }
    }
}
