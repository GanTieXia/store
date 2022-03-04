package com.gantiexia.security;

import com.gantiexia.userManage.entity.User;
import com.gantiexia.userManage.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

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

        // 查询出系统此账号的账号信息
        User userSys = loginMapper.getLoginUser(username);

        if(userSys == null){
            // 错误回显信息
            throw new BadCredentialsException("此用户不存在！");
        }

        if(userSys.getIsOnUse().equals("1")){
            //throw new UsernameNotFoundException("此用户已被禁用！");
            // 错误回显信息
            throw new BadCredentialsException("此用户已被禁用！请联系管理员...");
        }

        org.springframework.security.core.userdetails.User result =
                new org.springframework.security.core.userdetails.User(userSys.getIdNumber(),userSys.getPassword(), AuthorityUtils.createAuthorityList());

        return result;
    }
}
