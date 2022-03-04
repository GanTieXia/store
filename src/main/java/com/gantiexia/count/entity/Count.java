package com.gantiexia.count.entity;

import java.util.Date;

/**
 * 主页统计数据一览实体集
 *
 * @author GanTieXia
 * @date 2021/11/9 13:34
 */
public class Count {
    /** 主键*/
    private String id;
    /** 访问量*/
    private String visit;
    /** 统计时间*/
    private Date countTime;
    private String countTimeText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public Date getCountTime() {
        return countTime;
    }

    public void setCountTime(Date countTime) {
        this.countTime = countTime;
    }

    public String getCountTimeText() {
        return countTimeText;
    }

    public void setCountTimeText(String countTimeText) {
        this.countTimeText = countTimeText;
    }

    @Override
    public String toString() {
        return "Count{" +
                "id='" + id + '\'' +
                ", visit='" + visit + '\'' +
                ", countTime=" + countTime +
                ", countTimeText='" + countTimeText + '\'' +
                '}';
    }
}
