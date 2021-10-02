package com.testdemo.hashmap;

/**
 *  测试 MyHashMap
 *
 * @author GanTieXia
 * @date 2021/9/27 21:29
 */
public class TestMyHashMap {
    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<>();

        map.put("GanTieXia","肝铁侠");
        map.put("ZhuZhuXia","猪猪侠");
        map.put("ShanDianXia","闪电侠");

        System.out.println("{GanTieXia" + ":" + map.get("GanTieXia")+"}");
        System.out.println("{ZhuZhuXia" + ":" + map.get("ZhuZhuXia")+"}");
        System.out.println("{ShanDianXia" + ":" + map.get("ShanDianXia")+"}");

        System.out.println("-----------------------");

        System.out.println("map集合的大小:" + map.size());
    }
}
