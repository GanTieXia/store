package com.testdemo.threadTest;

/**
 * @author GanTieXia
 * @date 2021/9/17 22:48
 */
public class SaleThread extends Thread{
    // 商品初始库存量为100件
    public int INVENTORY_COUNT = 100;

    @Override
    synchronized public void run(){
        // 获取到线程拿到count时的值并保存下载做输出使用
        int beforeCount = INVENTORY_COUNT;
        // 假设每个人的下单数量为[1,10]件之间
        int saleCount = (int) ((Math.random()*10) + 1);
        // 减少库存量
        INVENTORY_COUNT = INVENTORY_COUNT - saleCount;
        // 库存量大于0的时候才可以继续卖出
        if(INVENTORY_COUNT >= 0){
            // 输出
            System.out.println("剩余库存量：" + beforeCount + "件。顾客" + Thread.currentThread().getName() + "购买"+ saleCount +"件商品。剩余库存量：" + INVENTORY_COUNT + "件。");
        } else if(INVENTORY_COUNT < 0) {
            // 当库存不够时
            System.out.println("剩余库存量：" + beforeCount + "件，顾客需求量："+ saleCount + "件。库存不足，请尽快补充库存！");
            // 销售不成功，库存量为这个顾客操作时的剩余库存量
            INVENTORY_COUNT = beforeCount;
        }
    }

    public static void main(String[] args) {

        SaleThread thread = new SaleThread();

        // 假设此处有26个顾客产生了购买行为
        for(int i=0;i<26;i++) {
            // 顾客姓名我们用大写英文字母表示
            char customer = (char)('A'+i);
            // 转换成字符转用来创建线程时设置顾客姓名
            String customerName = String.valueOf(customer);
            // 开始发生购买行为
            Thread purchaseBehavior = new Thread(thread,customerName);
            // 发生购买行为
            purchaseBehavior.start();
        }
    }
}
