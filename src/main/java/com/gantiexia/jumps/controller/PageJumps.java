package com.gantiexia.jumps.controller;

import com.gantiexia.commodity.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author GanTieXia
 * @date 2021/8/20 23:06
 */
@Controller
@RequestMapping("pageJumps")
public class PageJumps {

    @Autowired
    private CommodityService userService;

    /**
     * 跳转主页
     *
     * @return
     */
    @RequestMapping("/homepage")
    public String toHomepage(){
        return "/homepage/homepage";
    }

    /**
     * 跳转商品页
     *
     * @return
     */
    @RequestMapping("/toCommodityPage")
    public String toCommodityPage(){
        return "/commodity/commodityPage";
    }

    /**
     * 跳转商品页
     *
     * @return
     */
    @RequestMapping("/toManageCommodityPage")
    public String toManageCommodityPage(){
        return "/manage/manageCommodityPage";
    }

    /**
     * 跳转关于杂货铺
     *
     * @return
     */
    @RequestMapping("/toAboutStorePage")
    public String toAboutStorePage(){
        return "/about_store/aboutStore";
    }

}
