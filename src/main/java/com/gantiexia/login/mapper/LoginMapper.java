package com.gantiexia.login.mapper;

import com.gantiexia.login.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author GanTieXia
 * @date 2021/10/7 0:45
 */
@Mapper
public interface LoginMapper {
    /**
     * 核对登录密码
     * @param user
     * @return
     */
    User getUserMessage(User user);
}
