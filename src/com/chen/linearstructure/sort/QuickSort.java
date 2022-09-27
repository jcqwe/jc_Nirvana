package com.chen.linearstructure.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
//        int[] arr = {-9, 0, 0, 23, -567, 70};

        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后" + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;//最左边的下标
        int r = right;//右边的下标
        //中间值
        int middle = arr[(right + left) / 2];

        int temp = 0;
        //如果左边的下标大于等于右边的下标就退出循环
        while (l < r) {
            //在middle左边一直找,直到找到大于等于middle的值退出循环
            while (arr[l] < middle) {
                l++;
            }
            //在middle右边一直找,直到找到小于等于middle的值退出循环
            while (arr[r] > middle) {
                r--;
            }
            //如果l >= r说明middle左边全都是小于它的值右边全都是大于它的值
            //退出循环
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //交换完后发现arr[l] == middle 就让r--
            if (arr[l] == middle) {
                r--;
            }
            //交换完后发现arr[r] == middle 就让l++
            if (arr[r] == middle) {
                l++;
            }
        }
        // 如果 l == r, 必须l++, r--, 否则为出现栈溢出
        if(l == r){
            l++;
            r--;
        }

        //向左递归
        if(left < r){
            quickSort(arr,left,r);
        }
        //向右递归
        if(right > l){
            quickSort(arr,l,right);
        }
    }


    public static void model(int[] arr, int left, int right) {
        int l = left; //左下标
        int r = right; //右下标
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0; //临时变量，作为交换时使用
        //while循环的目的是让比pivot 值小放到左边
        //比pivot 值大放到右边
        while (l < r) {
            //在pivot的左边一直找,找到大于等于pivot值,才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot的右边一直找,找到小于等于pivot值,才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果l >= r说明pivot 的左右两的值，已经按照左边全部是
            //小于等于pivot值，右边全部是大于等于pivot值
            if (l >= r) {
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个arr[l] == pivot值 相等 r--， 前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值 相等 l++， 后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        // 如果 l == r, 必须l++, r--, 否则为出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            model(arr, left, r);
        }
        //向右递归
        if (right > l) {
            model(arr, l, right);
        }
    }
}
