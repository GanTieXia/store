package com.testdemo.threadTest;

/**
 * @author GanTieXia
 * @date 2021/9/17 20:51
 */
public class PrivateVariableThread extends Thread{
    private int count = 5;

    @Override
    synchronized public void run(){
        // 获取到线程拿到count时的值并保存下载做输出使用
        int beforeCount = count;
        // 操作count
        count--;
        // 输出
        System.out.println("线程操作前count的值：" + beforeCount + "。线程" + Thread.currentThread().getName() + "操作后的值：" + count);
    }

    public static void main(String[] args) {
        PrivateVariableThread thread = new PrivateVariableThread();
        // 创建五个线程
        Thread a = new Thread(thread,"A");
        Thread b = new Thread(thread,"B");
        Thread c = new Thread(thread,"C");
        Thread d = new Thread(thread,"D");
        Thread e = new Thread(thread,"E");
        // 启动线程
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();

    }
}
