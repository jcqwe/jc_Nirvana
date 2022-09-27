package com.chen.linearstructure.sort;

import java.util.Arrays;

/**
 * 插入排序(从小到大)
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {111,34,109,1,-1,20,30};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){
        /*
          插入排序:
          1.将arr看成两个数组一个有序的(从arr[0]开始)一个无序的(从arr[1]-arr[arr.length-1])
          2.每次循环把无序的插入到有序的中去
         */
//        int insertVal = arr[1];//要插入有序表中的无序数
//        int insertIndex = 1 - 1;//有序表中的下标
//        //1.insertIndex >= 0防止数组越界
//        //2.如果insertVal < arr[insertIndex]:无序数小于了有序表中的数
//        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
//            arr[insertIndex + 1] = arr[insertIndex];
//            insertIndex--;
//        }
//        arr[insertIndex + 1] = insertVal;
//
//        insertVal = arr[2];
//        insertIndex = 2 -1;
//        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
//            arr[insertIndex + 1] = arr[insertIndex];
//            insertIndex--;
//        }
//        arr[insertIndex + 1] = insertVal;


        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];//要插入有序表中的无序数
            int insertIndex = i - 1;//有序表中的下标
            //1.insertIndex >= 0防止数组越界
            //2.如果insertVal < arr[insertIndex]:无序数小于了有序表中的数
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
        }
    }
}
