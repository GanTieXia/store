package com.gantiexia.count.mapper;

import com.gantiexia.count.entity.Count;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author GanTieXia
 * @date 2021/11/9 13:36
 */
@Mapper
public interface CountMapper {

    /**
     * 统计今天的访问量
     *
     * @param count
     * @return
     */
    int insertTodayCountVisit(Count count);

    /**
     * 查询今天的统计数据
     *
     * @param
     * @param today
     * @return
     */
    Count selectTodayCount(String today);

    /**
     * 变更访问量
     *
     * @param count
     * @return
     */
    int updateCount(Count count);

    /**
     * 查询最近7天的访问量
     *
     * @param count
     * @return
     */
    List<Count> selectAllCount(Count count);
}
