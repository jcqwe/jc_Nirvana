package com.chen.linearstructure.tree;

/**
 * 顺序存储二叉树
 */
public class ArrBinaryTree {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        arrBinaryTree1 arrBinaryTree = new arrBinaryTree1(arr);
//        arrBinaryTree.preOrder();//1,2,4,5,3,6,7
//        arrBinaryTree.midOrder(0);//4,2,5,1,6,3,7
        arrBinaryTree.afterOrder(0);//4,5,2,6,7,3,1
    }
}

class arrBinaryTree1 {
    //存放二叉树结点
    private int[] arr;


    public arrBinaryTree1(int[] arr) {
        this.arr = arr;
    }
    //重载
    public void  preOrder(){
        this.preOrder(0);
    }

    //以前序遍历二叉树的形式遍历数组
    public void preOrder(int index) {
        /*
            1.第n个元素的左子结点,在第2*n+1个位置
            2.第n个元素的右子结点,在第2*n+2个位置
            1.第n个元素的父结点,在第(n-1)/2个位置
         */
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,不能进行前序遍历");
            return;
        }
        System.out.println(arr[index]);
        //向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }
        //向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            preOrder(index * 2 + 2);
        }
    }
    //以中序遍历二叉树的形式遍历数组
    public void midOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,不能进行中序遍历");
            return;
        }
        //向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            midOrder(index * 2 + 1);
        }
        System.out.println(arr[index]);
        //向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            midOrder(index * 2 + 2);
        }
    }
    //以后序遍历二叉树的形式遍历数组
    public void afterOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,不能进行后序遍历");
            return;
        }
        //向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            afterOrder(index * 2 + 1);
        }
        //向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            afterOrder(index * 2 + 2);
        }
        System.out.println(arr[index]);
    }
}
