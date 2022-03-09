package com.gantiexia.count.service.serviceImpl;

import com.gantiexia.count.entity.Count;
import com.gantiexia.count.mapper.CountMapper;
import com.gantiexia.count.service.CountService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author GanTieXia
 * @date 2021/11/9 13:41
 */
@Service
public class CountServiceImpl implements CountService {

    @Autowired
    private CountMapper countMapper;

    /**
     * 插入今天的访问量
     *
     * @param
     * @return
     */
    @Override
    synchronized public void insertCountVisit() {
        // 查询是否存在今天的统计数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Count todayCount = countMapper.selectTodayCount(sdf.format(System.currentTimeMillis()));

        if(todayCount == null){
            // 执行插入今天的第一条访问量数据
            Count countPram = new Count();
            // 时间为今天
            Date date = new Date();
            countPram.setCountTime(date);
            // 访问量
            countPram.setVisit("1");
            // 执行插入
            countMapper.insertTodayCountVisit(countPram);

        } else if(todayCount != null){
            // 执行修改，访问量+1操作
            String count = todayCount.getVisit();
            String countAdd = String.valueOf(Integer.valueOf(count) + 1);

            Count countPram = new Count();
            // 访问量
            countPram.setVisit(countAdd);
            // 执行修改
            countMapper.updateCount(countPram);

        }
    }

    /**
     * 查询最近7天的访问量统计数据
     *
     * @param count
     * @return
     */
    @Override
    public List<Count> selectAllCount(Count count) {
        return countMapper.selectAllCount(count);
    }
}
