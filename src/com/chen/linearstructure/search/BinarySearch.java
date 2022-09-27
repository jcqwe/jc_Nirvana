package com.chen.linearstructure.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
//        int[] arr = {1,10,12,89,1000,1234};
//        System.out.println("resIndex=" + binarySearch(arr,0,arr.length - 1,10));
        int[] arr = {1,10,12,89,1000,1000,1234};
        System.out.println("resIndexList=" + binarySearch2(arr,0,arr.length - 1,1000));
    }

    /**
     * 二分查找的前提是数组必须是有序的
     * @param arr 要进行查找的数组
     * @param left 左索引
     * @param right 右索引
     * @param findVal 需要查找的值
     * @return 找到就返回该值的索引,否则返回-1
     */
    public static int binarySearch(int[] arr, int left,int right,int findVal){
        /*
            思路:
            mid = (left + right) / 2;
            1.如果findVal > arr[mid],就向右递归继续查找
            2.如果findVal < arr[mid],就向左递归继续查找
            3.如果findVal == arr[mid],return mid
            4.如果没有在数组中找到这个值,left > right, return -1;
         */
        if(left > right){
            return -1;
        }
        int mid = (left + right) / 2;
        if(findVal > arr[mid]){
           return binarySearch(arr,mid + 1,right,findVal);
        }else if(findVal < arr[mid]){
            return binarySearch(arr,left,mid - 1,findVal);
        }else {
            return mid;
        }
    }

    /**
     * 思考:如果数组中有相同元素该怎么查找{1,10,12,89,1000,,1000,1000,1234}
     * 1.如果找到findVal==arr[mid]后不要马上返回mid的值,先把mid放到list集合中
     * 2.向mid左边扫描看有没有相同的findVal
     * 3.向mid右边扫描看有没有相同的findVal
     */
    public static List<Integer> binarySearch2(int[] arr, int left,int right,int findVal){
        if(left > right){
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        if(findVal > arr[mid]){
            return binarySearch2(arr,mid + 1,right,findVal);
        }else if(findVal < arr[mid]){
            return binarySearch2(arr,left,mid - 1,findVal);
        }else {
            List<Integer> resIndexList = new ArrayList<>();
            resIndexList.add(mid);
            //向左扫描
            int temp = mid - 1;
            while (true){
                if(temp < 0 || arr[temp] != findVal){
                    break;
                }
                resIndexList.add(temp);
                temp -= 1;
            }
            //向右扫描
            temp = mid + 1;
            while (true){
                if(temp > arr.length - 1  || arr[temp] != findVal){
                    break;
                }
                resIndexList.add(temp);
                temp += 1;
            }
            return resIndexList;
        }
    }
}
