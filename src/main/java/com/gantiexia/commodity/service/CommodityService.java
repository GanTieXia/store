package com.gantiexia.commodity.service;

import com.gantiexia.commodity.entity.Commodity;

import java.util.List;

/**
 * @author GanTieXia
 * @date 2021/8/21 4:36
 */
public interface CommodityService {

    /**
     * 获取信息
     *
     * @return
     */
    List<Commodity> getCommodityInformation();
}
