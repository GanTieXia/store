package com.testdemo.myhashmap;

import java.util.Map;
import java.util.Objects;

/**
 * @author GanTieXia
 * @date 2021/9/12 2:33
 */
public class MyHashMap<K,V> {

    /** HashMap的初始容量 */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // 1 << 4 = 16

    /** HashMap的最大容量*/
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /** HashMap到达容量的3/4时进行扩容机制*/
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /** 转换为树的阈值，应该大于2至少是8*/
    static final int TREEIFY_THRESHOLD = 8;

    /** 当小于这个数的时候红黑树再转化为链表的形式*/
    static final int UNTREEIFY_THRESHOLD = 6;

    /** 只有在数组的长度大于6时且链表长度大于8时,才能树形化链表*/
    static final int MIN_TREEIFY_CAPACITY = 64;

    /**
     * 定义Node
     *
     * @param <K>
     * @param <V>
     */
    static class Node<K,V> implements Map.Entry<K,V> {
        // hash值
        final int hash;
        // key
        final K key;
        // value
        V value;
        // 链表头节点
        Node<K,V> next;

        // 这里也只是简单地进行了赋值操作
        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        // 定义getKey()方法,返回key值
        public final K getKey()        { return key; }
        // 定义getValue()方法,返回key值
        public final V getValue()      { return value; }
        // 定义toString()方法,返回key=value值
        public final String toString() { return key + "=" + value; }

        // 按位异或运算符(^),将对应的值转换为二进制以后,比如0111^1111 = 1000,
        // 相同位取0,不同位取1.转化为二进制以后1000等于十进制的8
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        // 在setValue的时候,如果在同一个位置set两次，返回上一次的Value值
        // 例如如下这段代码,可以试一下输出结果
        // Map map = new HashMap();
        // map.put("1","第一次的");
        // System.out.println(map.put("1","第二次的"));
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        // 定义equals()方法
        public final boolean equals(Object o) {
            // 当if语句后面的逻辑语句只有一句的时候,{}可省略
            if (o == this)
                return true;
            // instanceof为二元运算符,左边为对象,右边为类,当对象是右边类或者子类的所创建的对象时,返回为true.否则返回为false
            if (o instanceof Map.Entry) {
                // 将o转换为Map.Entry<?,?>并且赋值给e
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                // 如果key和e.getKey()相等 并且 value和e.getValue()相等,返回true
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
}
