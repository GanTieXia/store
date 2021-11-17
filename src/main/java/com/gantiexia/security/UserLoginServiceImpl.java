package com.gantiexia.security;

import com.gantiexia.login.entity.User;
import com.gantiexia.login.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义用户认证
 *
 * @author GanTieXia
 * @date 2021/11/15 11:05
 */
@Component("myUserLoginServiceImpl")
public class UserLoginServiceImpl implements UserDetailsService {

    @Autowired
    private LoginMapper loginMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("自定义用户认证逻辑");

        // 查询出系统此账号的账号信息
        User userSys = loginMapper.getLoginUser(username);

        if(userSys == null){
            throw new UsernameNotFoundException("此用户不存在");
        }

        System.out.println("自定义用户认证逻辑登录用户："+ userSys.toString());

        org.springframework.security.core.userdetails.User result =
                new org.springframework.security.core.userdetails.User(userSys.getIdNumber(),userSys.getPassword(), AuthorityUtils.createAuthorityList());


        return result;
    }
}
