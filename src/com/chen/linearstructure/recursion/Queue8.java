package com.chen.linearstructure.recursion;

/**
 * 递归解决8皇后问题
 * 即8个皇后不能在同一行同一列或同一斜线
 */
public class Queue8 {
    int[] array = new int[8];//一维数组存放每个皇后的位置(存放每一个正确解)
    static int count = 0;
    public static void main(String[] args){
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("正确解:" + queue8.count);
    }
    /**
     *
     * @param n 从0开始
     */
    private  void check(int n){
        //如果n=8说明已经摆放到第9个皇后了,正确解发已经找到,打印并返回
        if(n == 8){
            print();
            count++;
            return;
        }
        //如果没有找到,就循环去找
        for (int i = 0; i < 8; i++) {
            array[n] = i;
            //如果不冲突,就递归摆放下一个皇后
            if(judge(n)){
                check(n + 1);
            }
            //如果冲突,进行i++判断下一个位置是否冲突
        }
    }
    //判断第n个皇后和前一个皇后位置是否冲突
    public boolean judge(int n){
        for (int i = 0; i < n; i++) {
            //1.array[i] == array[n]表示两个皇后在同一列
            //2.Math.abs(n - i) == Math.abs(array[n] - array[i])表示两个皇后在同一斜线
            if(array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }
    //打印一维数组
    public void print(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
