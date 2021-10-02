package com.testdemo.hashmap;

/**
 * 实体
 *
 * @author GanTieXia
 * @date 2021/9/27 21:26
 */
public class Entry<K,V> {
    /** HASH值*/
    int hash;
    /** key*/
    K key;
    /** value*/
    V value;
    /** 链表的指针*/
    Entry<K, V> next;

    /** 消费者*/
    public Entry(){}
    public Entry(K key, V value){
        this.key = key;
        this.value = value;
    }
    public Entry(int hash, K key, V value, Entry<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    /** get/set方法*/
    public K getKey() {
        return key;
    }
    public void setKey(K key) {
        this.key = key;
    }
    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }
}
