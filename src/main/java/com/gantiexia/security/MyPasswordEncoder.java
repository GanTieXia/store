package com.gantiexia.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author GanTieXia
 * @date 2021/11/16 10:44
 */
public class MyPasswordEncoder implements PasswordEncoder {
    /**
     * 加密
     * @param charSequence 页面收集的源密码
     * @return
     */
    @Override
    public String encode(CharSequence charSequence) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] tmp = digest.digest(charSequence.toString().getBytes());
            return toHexString(tmp);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 校验
     * @param charSequence
     * @param s
     * @return
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(encode(charSequence));
    }


    /**
     * 将加密后的密码转换为十六进制的字符串
     *
     * @param tmp
     * @return
     */
    private String toHexString(byte[] tmp){
        StringBuilder stringBuilder = new StringBuilder("");

        for(byte b : tmp){
            String s = Integer.toHexString(b & 0xFF);
            if(s.length() == 1){
                stringBuilder.append("0");
            }
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}
