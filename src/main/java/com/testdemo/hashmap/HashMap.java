package com.testdemo.hashmap;

/**
 *  这个 HashMap 支持 put / get / 扩容
 *
 * @author GanTieXia
 * @date 2021/9/27 21:27
 */
public class HashMap<K,V> {
    /** 初始容量值*/
    static final int INITIAL_CAPACITY = 4;
    /** 加载因子*/
    float LOAD_FACTOR = 0.75f;
    /** 记录map集合的容量*/
    int COUNT = 0;

    Entry<K, V>[] table;

    public K put(K key, V value) {

        Entry<K, V> newEntry = new Entry(key, value);

        // 这里我们没有写可以手动输入初始值的方法，所以直接设置默认容量
        if (table == null) {
            table = new Entry[INITIAL_CAPACITY];
        }

        // 数组下标位置
        int hash = hash(key);

        // 链表模型,如果Hash值相等,则形成链表
        // head代表链表头，此处用的头插法
        Entry<K, V> head = table[hash];

        // 当容量达到初始容量的加载因子时扩容
        if(COUNT > table.length * LOAD_FACTOR){
            resize();
        }

        // 注意数组+链表的概念模型将会再这里产生

        // 如果table[hash]这个位置没有元素，直接赋值
        if (head == null) {
            table[hash] = newEntry;
            COUNT++;
            return key;
        } else {
            // 如果table[hash]这个位置有元素
            Entry tail = new Entry<K, V>();
            // 如果这个元素的next指针为空，设置指针指向新的键值
            if (head.next == null) {
                head.next = newEntry;
            } else {
                // 如果指针next不为空，找到指针为空的链表上的那一个元素，插入
                do {
                    tail = head;
                } while ( (head = head.next) != null );
                // 该链表上此对象的指针指向新的对象
                tail.next = newEntry;
            }
            COUNT++;
            return key;
        }
    }

    /** 扩容*/
    public int resize() {
        // 扩容, << 2 代表 4倍 扩容
        int newCapacity = INITIAL_CAPACITY << 2;
        // 新建一个数组
        Entry[] newTable = new Entry[newCapacity];
        // 数组的copy方法
        System.arraycopy(table, 0, newTable, 0, table.length);
        // 新数组赋值给老数组
        this.table = newTable;
        // 返回新容量
        return newCapacity;
    }

    /** 获取 key 的键值*/
    public V get(K key) {
        Entry<K, V> entry;
        // 二元表达式通过getEntry()方法寻找键值
        return (entry = getEntry(hash(key), key)) == null ? null : entry.value;
    }

    /** 传入 hash 获取 key 值*/
    public Entry<K, V> getEntry(int hash, K key) {
        // 新建对象做逻辑判断
        Entry<K, V> entry = table[hash];
        // 如果 table[hash] 为空,证明不存在此 key 的键值
        if (entry == null) {
            return null;
        } else if (entry != null && entry.next == null) {
            // 如果此元素不为空,并且此元素的next指针为空，证明此链表上只有一个元素，直接返回
            return entry;
        } else if (entry.next != null) {
            // 如果此对象的 next 指针不为空，就要在此链表上去找到 hash 值相等的数据
            do {
                // 如果此元素的hash与该对象的key值的hash相等
                // 并且
                // key相等 或者 key不为空并且key相等
                if (hash == hash(entry.key) && (key == entry.key || (key != null && key.equals(entry.key)))) {
                    // 返回此链表上的对象
                    return entry;
                }
                // 条件是此对象的指针 next 不为空
            } while ( (entry = entry.next) != null );

            // 如果通过以上循环没有找到对象的对像的话，返回空
            return null;
        }
        return null;
    }

    /** 计算hash*/
    public final int hash(K key) {
        // &运算：4 & 5
        // 转化为二进制： 100 & 101 = 100 （同时为1才为1，否则为0）
        // 十进制：4
        // 所以： 4 & 5 = 4
        //
        // 0x7FFFFFFF代表int的最大值
        //
        // 此操作主要是为了取得正整数
        //
        // 后面 %运算 是为了使得返回值数组下标不会越界,即返回值小于数组或者扩容后数组的大小
        return (key == null) ? 0 : ( key.hashCode() & 0x7FFFFFFF % table.length);
    }

    /** 返回 map 集合大小的方法*/
    public int size(){
        return COUNT;
    }
}
