package com.chen;

import com.chen.leecode.arr.Array;

import java.util.Arrays;

/**
 * 总结排序算法
 */
public class Test {
    public static void main(String[] args) {
//        int[] bubbleArr = {10,20,5,3,2};
//        System.out.println(Arrays.toString(bubbleSort(bubbleArr)));
//        int[] quickArr = {-9, 78, 0, 23, -567, 70};
//        quickSort(quickArr, 0, quickArr.length - 1);
//        System.out.println(Arrays.toString(quickArr));
//        int [] insertArr = {-9, 78, 0, 23, -567, 70};
//        insertSort(insertArr);
//        System.out.println(Arrays.toString(insertArr));
//        int[] shellArr = {-9, 78, 0, 23, -567, 70};
//        shellSort(shellArr);
//        System.out.println(Arrays.toString(shellArr));
        int[] selectArr = {-9, 78, 0, 23, -567, 70};
        System.out.println(Arrays.toString(selectSort(selectArr)));
    }

    /**
     * 冒泡排序(时间复杂度O(n^2))升序
     *
     * @param arr 待排序数组
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;//优化冒泡排序
        //外层表示循环次数
        for (int i = 0; i < arr.length - 1; i++) {
            //每次循环比较的次数
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前一个元素大于后一个元素就交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
            //如果发生交换就继续向下执行
            if (flag) {
                flag = false;
            } else {//没有发生交换直接退出本次循环
                break;
            }
        }
        return arr;
    }

    /**
     * 快速排序(时间复杂度O(nlogn))升序
     *
     * @param arr   待排序数组
     * @param left  左下标
     * @param right 右下标
     */
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int middle = arr[(left + right) / 2];
        int temp = 0;
        while (l < r) {
            while (arr[l] < middle) {//从左边开始直到找到中间值左边大于或等于中间值的数退出循环
                l++;
            }
            while (arr[r] > middle) {//从右边开始直到找到中间值右边小于等于中间值的数退出循环
                r--;
            }
            if (l >= r) {//如果l >= r说明中间值的左边全部小于等于中间值 中间值的右边全部大于等于中间值
                break;
            }
            //开始交换
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;
            if (arr[l] == middle) {
                r--;
            }
            if (arr[r] == middle) {
                l++;
            }
        }
        //不做处理会出现栈溢出
        if (l == r) {
            l++;
            r--;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (l < right) {
            quickSort(arr, l, right);
        }
    }

    /**
     * 插入排序(时间复杂度O(n^2))升序
     *
     * @param arr 待排序数组
     */
    public static void insertSort(int[] arr) {
        //把数组看为两部分,一部分有序的,一部分无序的
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];//待插入数据
            int insertedIndex = i - 1;//有序表中的数据下标
            //insertedIndex >= 0防止数组越界异常
            while (insertedIndex >= 0 && insertVal < arr[insertedIndex]) {//待插入数据小于有序表中的数据时
                arr[insertedIndex + 1] = arr[insertedIndex];//有序表中的数据后移一位
                insertedIndex--;
            }
            arr[insertedIndex + 1] = insertVal;
        }
    }

    /**
     * 希尔排序(时间复杂度O(nlogn))升序 交换法
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int temp = 0;
        //gap步长外层循环表示步长变化,当步长=1时排序完成
        for (int gap = arr.length / 2; gap >= 1; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j + gap];
                        arr[j + gap] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
    }

    /**
     * 选择排序(时间复杂度O(n^2))升序
     *
     * @param arr
     * @return
     */
    public static int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;//最小值下标
            int min = arr[i];//假设第一个是最小值
            for (int j = arr.length - 1; j > i; j--) {
                if(arr[j] < min){
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
