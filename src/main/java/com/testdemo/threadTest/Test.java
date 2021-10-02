package com.testdemo.threadTest;

/**
 * @author GanTieXia
 * @date 2021/9/17 22:48
 */
public class Test {
    public static void main(String[] args) {

        for(int i=0;i<26;i++) {
            char a = (char)('A'+i);
            String b = String.valueOf(a);
            System.out.print(b);
        }
    }
}
