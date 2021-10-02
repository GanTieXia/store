package com.testdemo.myhashmap;

/**
 * @author GanTieXia
 * @date 2021/9/16 20:21
 */
public class ThreadAa extends Thread{
    private int a = 5;

    @Override
    synchronized public void run() {
        a--;
        System.out.println(a);
    }

    public static void main(String[] args) {
        ThreadAa threadAa = new ThreadAa();
        Thread aa = new Thread(threadAa, "A");
        Thread bb = new Thread(threadAa, "B");
        Thread cc = new Thread(threadAa, "C");
        Thread dd = new Thread(threadAa, "D");
        Thread ee = new Thread(threadAa, "E");

//        Thread aa = new ThreadAa();
//        Thread bb = new ThreadAa();
//        Thread cc = new ThreadAa();
//        Thread dd = new ThreadAa();
//        Thread ee = new ThreadAa();

        aa.start();
        bb.start();
        cc.start();
        dd.start();
        ee.start();

    }
}
