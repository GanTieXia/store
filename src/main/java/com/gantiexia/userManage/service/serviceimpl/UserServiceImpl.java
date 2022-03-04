package com.gantiexia.userManage.service.serviceimpl;

import cn.hutool.extra.mail.MailUtil;
import com.gantiexia.authcode.entity.AuthCode;
import com.gantiexia.authcode.mapper.AuthCodeMapper;
import com.gantiexia.userManage.entity.User;
import com.gantiexia.userManage.mapper.LoginMapper;
import com.gantiexia.userManage.service.UserService;
import com.gantiexia.redis.RedisUtils;
import io.netty.util.internal.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author GanTieXia
 * @date 2021/10/7 0:55
 */
@Service
public class UserServiceImpl implements UserService {

    // 添加日志打印
    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private AuthCodeMapper authCodeMapper;
    @Autowired
    private RedisUtils redisUtils;

    /** 存储图片的名字*/
    private String fileName;

    /** 初始idNumber,第一个生成的*/
    private final String idNumberStart = "10000001";

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public Map<String,String> getUserMessage(User user, HttpServletRequest request) {

        // 创建一个Map集合装处理结果
        Map map = new HashMap(2);

        // 查询出系统此账号的账号信息
        List<User> userSysList = loginMapper.getUserMessage(user);

        User userSys = userSysList.get(0);

        // 被禁用的用户
        if(userSys.getIsOnUse().equals("1")){
            map.put("code","505");
            map.put("message","此用户已被禁用");
            return map;
        }

        if(userSysList.size() == 0){
            map.put("code","404");
            map.put("message","此用户不存在");
            return map;
        }

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
    public String showUserMessage() {
        // Security中存储账号的对象
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 返回Security中存储的账号信息
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * 获取当前登录用户
     *
     * @param idNumber
     * @return
     */
    @Override
    public User getLoginUser(String idNumber) {
        return loginMapper.getLoginUser(idNumber);
    }

    /**
     * 返回登陆人信息
     *
     * @param idNumber
     * @return
     */
    @Override
    public User getPersonInfo(String idNumber) {
        return loginMapper.getPersonInfo(idNumber);
    }

    /**
     * 填充注册账号输入框
     *
     * @return
     */
    @Override
    public String getNextIdNumber() {
        String nextIdNumber = "";
        // 查询用户表
        User user = new User();
        List<User> userList = loginMapper.getUserMessage(user);
        if(userList.size() == 0){
            nextIdNumber = idNumberStart;
        } else if(userList.size() > 0){
            // 当前的ID账号最大值
            String idNumber = loginMapper.getNextIdNumber();
            // 在此基础上+1
            nextIdNumber = String.valueOf(Long.valueOf(idNumber)+1);
        }
        return nextIdNumber;
    }

    /**
     * 发送并存储验证码
     *
     * @param authCode
     * @return
     */
    @Override
    public Map<String,String> checkCode(AuthCode authCode) {
        // 验证码发送结果状态回显
        Map<String,String> map = new HashMap<>();
        // 生成6位随机邮件验证码
        StringBuffer authCodes = new StringBuffer();
        for(int j = 0; j< 6; j++){
            authCodes.append((int)((Math.random()*10)))  ;
        }
        // 将验证码发送至redis
        if(StringUtil.isNullOrEmpty(redisUtils.get(authCode.getEmail()))){
            // 将验证码放入redis,失效时间为180s
            redisUtils.setKeyTimeOut(authCode.getEmail(),String.valueOf(authCodes),180, TimeUnit.SECONDS);
            logger.info("验证码发送至redis，验证码："+redisUtils.get(authCode.getEmail()));
        } else if(!StringUtil.isNullOrEmpty(redisUtils.get(authCode.getEmail()))){
            map.put("code","500");
            map.put("message","已发送验证码，请勿重复发送");
            return map;
        }
        // 每个邮箱只能绑定一个用户
        User user = new User();
        List<User> userList = loginMapper.getUserMessage(user);
        List<User> sameEmailList = new ArrayList<>();
        for(User userPram : userList){
            if(userPram.getEmail() != null){
                if(userPram.getEmail().equals(authCode.getEmail())){
                    sameEmailList.add(userPram);
                }
            }
        }
        // 当重复绑定一个邮箱时
        if(sameEmailList.size() > 0){
            map.put("code","505");
            map.put("message","此邮箱已被绑定");
            return map;
        }
        // 如果成功走到这里，发送邮件
        map.put("code","200");
        map.put("message","验证码发送成功");
        // 当验证码生成成功时才发送验证码
        MailUtil.send(authCode.getEmail(), "您的注册信息", "欢迎您的注册！<br>您的注册验证码为:<label style=\"color: red\">"+authCodes+"</label>" +
                "<br>请妥善保管，防止丢失！<br>如不是您本人操作，请忽略！", true);
        return map;
    }

    /**
     * 头像上传功能
     *
     * @param multipartFile
     * @param user
     * @return
     */
    @Override
    public Map<String, String> saveUploadPicture(MultipartFile multipartFile, User user) {
        // 返回结果参数封装
        Map<String,String> map = new HashMap<>();

        // 此行改为所在工程的对应的上传物理路径
        String uploadAbsolutePath = "/storeProject/image";
        // 在对应的image包下按照图片生成日期进行分包
        Date date = new Date();
        // 转换成时间字符串
        SimpleDateFormat sdl = new SimpleDateFormat("yyyyMMdd");
        String nowDay = sdl.format(date);
        // 再将本地路径字符串拼接
        uploadAbsolutePath += "/" + nowDay;
        // 将我们想要保存的路径创建到本地
        File file = new File(uploadAbsolutePath);
        file.setWritable(true, false);

        // 如果文件夹不存在就创建文件夹
        if (!file.exists()) {
            file.mkdirs();
            logger.info("文件夹创建成功...");
        }

        //原文件名
        String names = multipartFile.getOriginalFilename();
        // 文件扩展名
        String fileExt = names.substring(names.lastIndexOf(".") + 1).toLowerCase();
        String newName = "";
        // 文件上传后的新名
        if(user.getIdNumber() != null){
            newName = user.getIdNumber() + "." + fileExt;
        } else {
            String idNumber = SecurityContextHolder.getContext().getAuthentication().getName();
            // 如果账号为空，就是修改头像。修改头像则从security中获取IdNumber
            newName = idNumber + "." + fileExt;
            // 再修改数据库中本人的头像路径
            User userPram = new User();
            userPram.setIdNumber(idNumber);
            // 系统默认路径，可自己设置路径
            userPram.setPersonagePicture("/storeProject/image/" + nowDay + "/" + newName);
            loginMapper.updatePersonPhoto(userPram);
        }

        fileName = newName;
        // 文件的绝对路径File
        File uploadFile = new File(uploadAbsolutePath + "/" + newName);

        // 执行文件保存到本地
        try {
            // 将上传的保存到本地指定路径
            FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(uploadFile));
            logger.info("图片保存成功！");
        } catch (Exception e) {
            // IOException代表文件上传的时候出错
            logger.info("图片保存到文件夹中出错！");
            logger.info(e);
        }

        map.put("code","200");
        map.put("message","上传文件成功！");
        return map;
    }

    /**
     * 注册功能
     *
     * @param user
     * @return
     */
    @Override
    public Map<String, String> register(User user) {
        // 返回消息集合
        Map<String,String> map = new HashMap<>();

        // 当多次点击注册按钮时
        User userPram = new User();
        userPram.setIdNumber(user.getIdNumber());
        List<User> userList = loginMapper.getUserMessage(userPram);
        if(userList.size() > 0){
            map.put("code","404");
            map.put("message","您已注册");
            return map;
        }

        // 设置头像路径
        Date date = new Date();
        // 转换成时间字符串
        SimpleDateFormat sdl = new SimpleDateFormat("yyyyMMdd");
        String nowDay = sdl.format(date);
        String personagePicture = "/storeProject/image/" + nowDay + "/" + fileName;
        user.setPersonagePicture(personagePicture);
        // 设置系统时间
        user.setCreateTime(date);
        // 默认有效
        user.setIsOnUse("0");

        // 从redis中取得验证码
        String authCode = redisUtils.get(user.getEmail());

        // 如果redis中的验证码不存在，提示验证码失效，请重新发送
        if(StringUtil.isNullOrEmpty(authCode)){
            map.put("code","404");
            map.put("message","验证码失效，请重新发送");
            return map;
        }

        // 密码加密方式：MD5
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] tmp = digest.digest(user.getPassword().getBytes());

            // 转换为以十六进制的无符号整数形式，用一个整数参数的字符串表示形式
            // 可自行定义，但是要与SpringSecurity中的密码校验方式相同
            StringBuilder passWord = new StringBuilder("");
            for(byte b : tmp){
                String s = Integer.toHexString(b & 0xFF);
                if(s.length() == 1){
                    passWord.append("0");
                }
                passWord.append(s);
            }

            user.setPassword(passWord.toString());

        } catch (NoSuchAlgorithmException e) {
            logger.info("密码加密失败...");
            e.printStackTrace();
        }

        // 如果缓存中的验证码与传回来的验证码相等，执行插入，完成注册
        if(authCode.equals(user.getAuthCode())){
            // 执行插入
            int n = loginMapper.register(user);
            if(n == 1){

                // 注册成功,如果缓存未过期,清除redis中的缓存
                String authCodeNow = redisUtils.get(user.getEmail());
                if(authCodeNow != null){
                    redisUtils.delete(user.getEmail());
                }

                map.put("code","200");
                map.put("message","注册成功");
                return map;
            } else {
                map.put("code","404");
                map.put("message","注册失败");
                return map;
            }
        } else {
            map.put("code","404");
            map.put("message","验证码错误");
            return map;
        }
    }

    /**
     * 用户表查询
     *
     * @param user
     * @return
     */
    @Override
    public Map<String,Object> getUserMessageList(User user,int page,int limit) {
        // 查询参数
        Map<String,Object> map = new HashMap<>();
        map.put("userName",user.getUserName());
        map.put("idNumber",user.getIdNumber());
        map.put("page",(page-1)*limit);
        map.put("limit",limit);

        Map<String,Object> mapResult = new HashMap<>();
        mapResult.put("code","0");
        mapResult.put("message","操作成功");
        mapResult.put("data",loginMapper.getUserMessageList(map));
        mapResult.put("count",loginMapper.getUserMessageListCount(map));

        return mapResult;
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    @Override
    public Map<String, String> delUser(User user) {
        int n = loginMapper.delUser(user.getId());
        return resultMap(n);
    }

    /**
     * 禁用用户
     * @param user
     * @return
     */
    @Override
    public Map<String, String> updateIsOnUse(User user) {
        int n = loginMapper.updateIsOnUse(user.getId());
        return resultMap(n);
    }

    /**
     * 解除禁用
     *
     * @param user
     * @return
     */
    @Override
    public Map<String, String> updateOnUse(User user) {
        int n = loginMapper.updateOnUse(user.getId());
        return resultMap(n);
    }

    /**
     * 返回结果集 -- 公共方法
     *
     * @param n
     * @return
     */
    public Map<String,String> resultMap(int n){
        Map<String,String> map = new HashMap<>();
        if(n == 1){
            map.put("code","200");
            map.put("message","操作成功！");
        } else {
            map.put("code","404");
            map.put("message","操作失败！");
        }
        return map;
    }
}
