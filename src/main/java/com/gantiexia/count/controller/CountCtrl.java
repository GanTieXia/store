package com.gantiexia.count.controller;

import com.gantiexia.count.entity.Count;
import com.gantiexia.count.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 平台主页统计
 *
 * @author GanTieXia
 * @date 2021/11/9 13:22
 */
@Controller
@RequestMapping("count")
public class CountCtrl {

    @Autowired
    private CountService countService;

    /**
     * 查询最近7天的访问量数据
     *
     * @return
     */
    @RequestMapping("/recentlyCount")
    @ResponseBody
    public List<Count> recentlyCount(Count count){
        return countService.selectAllCount(count);
    }
}
