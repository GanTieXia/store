package com.testdemo.tree;

/**
 * @author GanTieXia
 * @date 2021/9/21 19:50
 */
public class Test {
    public static void main(String[] args) {

        int[] value = new int[100000];
        for(int i=0;i<100000;i++){
            value[i] = i;
        }

        long startTime = System.currentTimeMillis();    //获取开始时间

        for(int i = 0;i<value.length;i++){
            System.out.println(value[i]);
        }

        long endTime = System.currentTimeMillis();    //获取结束时间

        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    }
}
