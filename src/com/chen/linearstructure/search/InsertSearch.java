package com.chen.linearstructure.search;

import java.util.Arrays;

/**
 * 插值查找(有序数组)
 * int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
 */
public class InsertSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
//        System.out.println(Arrays.toString(arr));
        int i = insertSearch(arr, 0, arr.length - 1, 100);
        System.out.println(i);
    }

    public static int insertSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("插值查找次数");
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        if (findVal > arr[mid]) {
            return insertSearch(arr, mid + 1, right, findVal);
        } else if (findVal < arr[mid]) {
            return insertSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
