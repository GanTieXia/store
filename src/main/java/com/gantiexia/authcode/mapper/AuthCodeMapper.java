package com.gantiexia.authcode.mapper;

import com.gantiexia.authcode.entity.AuthCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author GanTieXia
 * @date 2021/10/17 15:09
 */
@Mapper
public interface AuthCodeMapper {
    /**
     * 查询所有验证码集合
     *
     * @return
     */
    List<AuthCode> selectAuthCodeInformation(AuthCode authCode);

    /**
     * 生成对应的验证码
     *
     * @param authCode
     * @return
     */
    int insertAuthCode(AuthCode authCode);
}
