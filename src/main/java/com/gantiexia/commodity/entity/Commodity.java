package com.gantiexia.commodity.entity;

/**
 * 商品实体集
 *
 * @author GanTieXia
 * @date 2021/8/21 4:20
 */
public class Commodity {
    /** 主键*/
    private String id;
    /** 商品名称*/
    private String commodityName;
    /** 初始库存*/
    private String inventoryBegin;
    /** 剩余库存*/
    private String inventoryEnd;
    /** 创建时间*/
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getInventoryBegin() {
        return inventoryBegin;
    }

    public void setInventoryBegin(String inventoryBegin) {
        this.inventoryBegin = inventoryBegin;
    }

    public String getInventoryEnd() {
        return inventoryEnd;
    }

    public void setInventoryEnd(String inventoryEnd) {
        this.inventoryEnd = inventoryEnd;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
