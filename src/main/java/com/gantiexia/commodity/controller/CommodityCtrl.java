package com.gantiexia.commodity.controller;

import com.gantiexia.commodity.entity.Commodity;
import com.gantiexia.commodity.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author GanTieXia
 * @date 2021/8/20 23:06
 */
@Controller
@RequestMapping("commodity")
public class CommodityCtrl {

    @Autowired
    private CommodityService userService;

    /**
     * 查询商品信息
     *
     * @return
     */
    @RequestMapping("/getCommodityInformation")
    @ResponseBody
    public List<Commodity> getCommodityInformation(){
        return userService.getCommodityInformation();
    }
}
