package com.chen.linearstructure.tree;

import java.util.Arrays;

/**
 * 堆排序
 * 1.升序,先将待排序数组转换成以大顶堆的形式存放的数组
 * 2.降序,先将待排序数组转换成以小顶堆的形式存放的数组
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9,-1,-999,500};
        heapSort(arr);
    }

    //堆排序
    public static void heapSort(int[] arr) {
        int temp;
        //for循环将数组调整为大顶堆的方式
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapArr(arr, i, arr.length);
        }
        /*
        1.将堆顶元素和末端进行交换
        2.重新调整结构,使其满足堆定义,继续交换堆顶和末端元素
         */
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            heapArr(arr,0,j);
        }
        System.out.println(Arrays.toString(arr));
    }

    //将待排序数组转换成以大顶堆的方式存放

    /**
     * @param arr    待转换数组
     * @param i      非叶子结点
     * @param length 元素个数
     */
    public static void heapArr(int[] arr, int i, int length) {
        int temp = arr[i];

        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //如果i结点的左子结点小于右子结点
            if ((k + 1) < length && arr[k] < arr[k + 1]) {
                k++;//k变为右子结点
            }
            //得到左右结点的最大值
            //如果子结点大于当前结点就交换
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;//i指向右子结点
            } else {
                break;
            }
        }
        //当for循环结束后,交换完成
        arr[i] = temp;
    }
}
