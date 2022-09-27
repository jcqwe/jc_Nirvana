package com.chen.linearstructure.search;

import java.util.Arrays;

/**
 * 斐波那契查找(有序数组)
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int i = fibonacciSearch(arr, 0, arr.length - 1, 1000);
        System.out.println(i);
    }

    //获取一个斐波那契数列
    //f(k) = f(k-1)+f(k-2);
    public static int[] getFibonacci() {
        int[] fibonacci = new int[maxSize];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
        for (int i = 2; i < fibonacci.length; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci;
    }

    //斐波那契查找
    public static int fibonacciSearch(int[] arr, int left, int right, int findVal) {
        int k = 0;//获取斐波那契分割值的下标
        int mid = 0;
        int[] fibonacci = getFibonacci();//斐波那契数列
        while (right > fibonacci[k] - 1) {
            k++;
        }
        //因为fibonacci[k]数组有可能大于arr,所以创建一个新的数组把arr的值赋给新的数组,其他地方补0
        int[] temp = Arrays.copyOf(arr, fibonacci[k]);
        //使用arr[right]的值来填充
        //即:{1,8,10,89,1000,1234,0,0,0} => {1,8,10,89,1000,1234,1234,1234,1234}
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }
        while (left <= right) {
            mid = left + fibonacci[k - 1] - 1;
            if (findVal < temp[mid]) {//向左边查找
                right = mid - 1;
                //全部元素= 前面的元素 + 后面的元素
                //f[k] = f[k-1] + f[k-2]
                //前面有f[k-1]个元素所以继续拆分为f[k-1] = f[k-2]+f[k-3]
                //及mid = f[k-1-1]-1
                //k--;
                k--;
            } else if (findVal > temp[mid]) {//向右边查找
                left = mid + 1;
                //全部元素= 前面的元素 + 后面的元素
                //f[k] = f[k-1] + f[k-2]
                //后面有f[k-2]个元素,所以继续拆分为f[k-1] = f[k-3]+f[k-4]
                //及mid = f[k-1-2]-1
                //k-=2;
                k -= 2;
            } else {
                if (mid < right) {
                    return mid;
                } else {
                    return right;
                }
            }
        }
        return -1;
    }
}
