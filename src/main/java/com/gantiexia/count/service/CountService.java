package com.gantiexia.count.service;

import com.gantiexia.count.entity.Count;

import java.util.List;

/**
 * @author GanTieXia
 * @date 2021/11/9 13:42
 */
public interface CountService {

    /**
     * 插入当天的访问统计量
     *
     * @param
     * @return
     */
    int insertCountVisit();

    /**
     * 查询最近7天的访问量
     *
     * @param count
     * @return
     */
    List<Count> selectAllCount(Count count);
}
