package com.chen.linearstructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序(从小到大)
 */
public class SelectSort {
    public static void main(String[] args){
//        int[] arr = {34,111,109,1};
//        System.out.println("排序前" + Arrays.toString(arr));
//        int[] sort = selectSort(arr);
//        System.out.println("排序后" + Arrays.toString(sort));


        //测试选择排序的速度
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 80000);//随机生成[0,80000)之间的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间" + date1Str);

        selectSort(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序前的时间" + date2Str);
    }

    public static int[] selectSort(int[] arr){
        /*
            选择排序:
            1.第一次循环从34,111,109,1中找到最小的数和arr[0]进行交换 1,111,109,34
            2.第二次循环从1,111,109,34中找到最小的数和arr[1]进行交换 1,34,109,111
            3.第三次循环从1,34,109,111中找到最小的数和arr[2]进行交换 1,34,109,111
         */
//        int minIndex = 0;//最小值的下标
//        int min = arr[0];//假定第一个值是最小值
//        //第一次循环
//        for (int i = 1; i < arr.length; i++) {
//            if(min > arr[i]){
//                minIndex = i;
//                min = arr[i];
//            }
//        }
//
//        //交换
//        arr[minIndex] = arr[0];
//        arr[0] = min;
//
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;//最小值的下标
            int min = arr[i];//假定第一个值是最小值
            for (int j = arr.length - 1; j > i; j--) {
                if(min > arr[j]){
                    minIndex = j;
                    min = arr[j];
                }
            }
            //交换
            if(minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
        return arr;
    }
}
