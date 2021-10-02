package com.gantiexia.commodity.mapper;

import com.gantiexia.commodity.entity.Commodity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author GanTieXia
 * @date 2021/8/21 4:30
 */
@Mapper
public interface CommodityMapper {
    /**
     * 获取信息
     *
     * @return
     */
    List<Commodity> getCommodityInformation();
}
