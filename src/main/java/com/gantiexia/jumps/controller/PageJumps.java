package com.gantiexia.jumps.controller;

import com.gantiexia.count.service.CountService;
import com.gantiexia.userManage.entity.User;
import com.gantiexia.userManage.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author GanTieXia
 * @date 2021/8/20 23:06
 */
@Controller
@RequestMapping("store")
public class PageJumps {

    @Autowired
    private CountService countService;
    @Autowired
    private LoginMapper loginMapper;

    /**
     * 跳转主页
     *
     * @return
     */
    @RequestMapping("/homepage")
    public String toHomepage(){
        // 记录访问量信息，没访问一次这个网址，访问量增加1
        countService.insertCountVisit();
        // 跳转页面
        return "homepage/homepage";
    }

    /**
     * 跳转商品页
     *
     * @return
     */
    @RequestMapping("/toCommodityPage")
    public String toCommodityPage(){
        return "commodity/commodityPage";
    }

    /**
     * 跳转商品页
     *
     * @return
     */
    @RequestMapping("/toManageCommodityPage")
    public String toManageCommodityPage(){
        return "manage/manageCommodityPage";
    }

    /**
     * 跳转关于杂货铺
     *
     * @return
     */
    @RequestMapping("/toAboutStorePage")
    public String toAboutStorePage(){
        return "about_store/aboutStore";
    }

    /**
     * 跳转登录页面
     *
     * @return
     */
    @RequestMapping("/loginPage")
    public String toLoginPage(){
        return "login/loginPage";
    }

    /**
     * 主页加载系统统计图表界面
     *
     * @return
     */
    @RequestMapping("/countPage")
    public String toCountPage(){
        return "commodity/countPage";
    }

    /**
     * 个人主页
     *
     * @return
     */
    @RequestMapping("/personalPage")
    public String toPersonalPage(ModelMap map){
        User user = loginMapper.getPersonInfo(SecurityContextHolder.getContext().getAuthentication().getName());
        map.put("user",user);
        return "personal/personalPage";
    }

    /**
     * 通过账号查询用户信息
     *
     * @return
     */
    @RequestMapping("/showPersonalMessage")
    public User showPersonalMessage(String id){
        User user = loginMapper.getPersonInfo(id);
        return user;
    }

    /**
     * 个人主页
     *
     * @return
     */
    @RequestMapping("/userManage")
    public String toUserManage(){
        return "system/userManage";
    }

    /**
     * 测试前端效果界面
     *
     * @return
     */
    @RequestMapping("/toTestPage")
    public String toTestPage(){
        return "test/index";
    }
}
