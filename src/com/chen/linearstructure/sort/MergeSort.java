package com.chen.linearstructure.sort;

import java.util.Arrays;

/**
 *
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, (mid + 1), right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    //合并

    /**
     * @param arr   待排序数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//左边有序序列的初始索引
        int j = mid + 1;//右边有序序列的初始索引
        int t = 0;//temp的索引
        //1.先把左右两边按照规则填充到temp数组中,直到左右两边的有序序列有一边处理完为止
        while (i <= mid && j <= right) {
            //如果左边的元素小于等于了右边的元素就把小的元素存入到temp中
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        //2.将剩余一边的有序序列填充到temp中
        while (i <= mid) {//剩余的是左边的有序序列
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {//剩余的是右边的有序序列
            temp[t] = arr[j];
            t++;
            j++;
        }
        //3.将temp数组中的元素拷贝到arr中;注意:并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        System.out.println("tempLeft=" + tempLeft + ",right=" + right);
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
