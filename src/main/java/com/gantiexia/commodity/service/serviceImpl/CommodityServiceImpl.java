package com.gantiexia.commodity.service.serviceImpl;

import com.gantiexia.commodity.entity.Commodity;
import com.gantiexia.commodity.mapper.CommodityMapper;
import com.gantiexia.commodity.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author GanTieXia
 * @date 2021/8/21 4:36
 */
@Service
@Transactional
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper userMapper;

    /**
     * 获取所有信息
     *
     * @return
     */
    @Override
    public List<Commodity> getCommodityInformation() {
        return userMapper.getCommodityInformation();
    }
}
