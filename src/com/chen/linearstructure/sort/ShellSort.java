package com.chen.linearstructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序(从小到大)
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 80000);//随机生成[0,80000)之间的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间" + date1Str);
        //交换法
//        shellSwap(arr);
        //移位法
        shellSwap2(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间" + date2Str);
    }

    /*
      希尔排序:
        1.第一次循环把arr分为arr.length/2=5组,每组两个元素,两两比较将较小的元素放在前面[3, 5, 1, 6, 0, 8, 9, 4, 7, 2]
        2.第二次循环把arr分为5/2=2组,[0, 2, 1, 4, 3, 5, 7, 6, 9, 8]
        3.第三次循环 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
     */
    //交换法
    public static void shellSwap(int[] arr) {
        int temp = 0;
//        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //分为5组,步长为5
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果每组中大的元素在前面就交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.println("第" + (++count) + "次希尔排序后" + Arrays.toString(arr));
        }
//         //第一次希尔排序
////        for (int i = 5; i < arr.length; i++) {
////            //分为5组,步长为5
////            for (int j = i - 5; j >= 0; j -= 5) {
////               //如果每组中大的元素在前面就交换
//                if (arr[j] > arr[j + 5]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 5];
//                    arr[j + 5] = temp;
//                }
//            }
//        }
//        System.out.println("第一次希尔排序后" + Arrays.toString(arr));
//
//        //第二次希尔排序
//        for (int i = 2; i < arr.length; i++) {
//            //分为5组,步长为5
//            for (int j = i - 2; j >= 0; j -= 2) {
//                //如果每组中大的元素在前面就交换
//                if (arr[j] > arr[j + 2]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 2];
//                    arr[j + 2] = temp;
//                }
//            }
//        }
//        System.out.println("第二次希尔排序后" + Arrays.toString(arr));
//
//        //第三次希尔排序
//        for (int i = 1; i < arr.length; i++) {
//            //分为5组,步长为5
//            for (int j = i - 1; j >= 0; j -= 1) {
//                //如果每组中大的元素在前面就交换
//                if (arr[j] > arr[j + 1]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//        }
//        System.out.println("第三次希尔排序后" + Arrays.toString(arr));
    }
    //移动法
    public static void shellSwap2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;//待插入元素的下标
                int temp = arr[j];//待插入元素的值
                if(arr[j] < arr[j - gap]){
                    while (j - gap >= 0 && temp < arr[j - gap]){
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //退出循环时放到位置
                    arr[j] = temp;
                }
            }
        }
    }
}
