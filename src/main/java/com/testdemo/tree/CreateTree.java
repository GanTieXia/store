package com.testdemo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GanTieXia
 * @date 2021/9/21 19:07
 */
public class CreateTree {

    // 创建二叉树
    public static TreeNode creatTree(int[] value){
        // 新建以及集合方便做数据处理
        List<TreeNode> list = new ArrayList<>();

        for(int i=0;i < value.length;i++){
            TreeNode node = new TreeNode(value[i]);
            list.add(node);
        }

        // 新建一个对象
        TreeNode root;
        // 为了保证不报错下标越界异常,取i<list.size()/2的值运算
        for(int i=0;i < list.size()/2;i++){
            root = list.get(i);
            TreeNode left = list.get(i*2+1);
            root.leftChild = left;

            if(i*2+2 < list.size()){
                TreeNode right = list.get(i*2+2);
                root.rightChild = right;
            }
        }
        return list.get(0);
    }

    // 前序(根-左-右)遍历的方法
    public void preOrder(TreeNode root){
        if(root != null){
            System.out.println(root.data);
            preOrder(root.leftChild);
            preOrder(root.rightChild);
        }
    }

    public static void main(String[] args) {
        // 创建传入数组
        int[] value = new int[]{1,2,3,4,5};
        // 创建此类对象
        CreateTree createTree = new CreateTree();
        // 调用创建方法
        TreeNode root = createTree.creatTree(value);
        // 调用遍历输出方法
        createTree.preOrder(root);
    }
}
