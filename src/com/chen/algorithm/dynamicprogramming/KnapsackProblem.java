package com.chen.algorithm.dynamicprogramming;

/**
 * 动态规划解决背包问题(一个物品只能装一次)
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品的重量
        int[] val = {1500, 3000, 2000};//物品的价值
        int capacity = 4;//背包的容量
        int n = val.length;//物品的个数
        int[][] v = new int[n + 1][capacity + 1];//二维数组,行对应物品,列对应重量

        int[][] res = new int[n + 1][capacity + 1];//存放结果;

        //1.初始化第一行第一列为0即v[0][]=v[][0]=0
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        //
        for (int i = 1; i < v.length; i++) {//从第二行开始
            for (int j = 1; j < v[0].length; j++) {//从第二列开始
                if (w[i - 1] > j) {//如果物品的重量大于了背包容量
                    v[i][j] = v[i - 1][j];
                } else {//物品的重量小于等于背包容量
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        res[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        for (int[] ints : v) {
            for (int i = 0; i < ints.length; i++) {
                System.out.print(ints[i] + " ");
            }
            System.out.println();
        }
        System.out.println("==================");
        //输出结果
        int i = res.length - 1;//行的最后一个
        int j = res[0].length - 1;//列的最后一个
        while (i > 0 && j > 0) {
            if (res[i][j] == 1) {
                System.out.println("第" + i + "个物品放入背包");
                j -= w[i - 1];
            }
            i--;
        }
    }
}
