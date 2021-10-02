package com.testdemo.myhashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GanTieXia
 * @date 2021/9/11 22:36
 */
public class Test {

    public static void main(String[] args) {
       String[] arr = new String[5];

       int hash = "1000".hashCode();

       // map覆盖
       Map map = new HashMap();
       map.put("1","第一次的");
       System.out.println(map.put("1","第二次的"));



       System.out.println("学Java".hashCode());

       System.out.println(hash % arr.length);
    }
}
