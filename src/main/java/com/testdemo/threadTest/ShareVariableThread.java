package com.testdemo.threadTest;

/**
 * @author GanTieXia
 * @date 2021/9/17 20:52
 */
public class ShareVariableThread extends Thread{
    public int count = 5;

    @Override
    public void run(){
        // 获取到线程拿到count时的值并保存下载做输出使用
        int beforeCount = count;
        // 操作count
        count--;
        // 输出
        System.out.println("线程操作前count的值：" + beforeCount + "。线程" + Thread.currentThread().getName() + "操作后的值：" + count);
    }

    public static void main(String[] args) {
        // 创建五个线程
        Thread a = new ShareVariableThread();
        Thread b = new ShareVariableThread();
        Thread c = new ShareVariableThread();
        Thread d = new ShareVariableThread();
        Thread e = new ShareVariableThread();
        // 启动线程
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}
