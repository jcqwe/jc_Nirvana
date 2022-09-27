package com.chen.algorithm.binarysearch;

/**
 * 二分查找(非递归)和递归的实现
 */
public class BinarySearch {
    public static void main(String[] args){
        int[] arr = {1,8,20,30,50,100};
//        System.out.println("目标下标= " + binarySearch(arr,50));
        System.out.println("目标下标= " + binarySearch2(arr,0,arr.length-1,100));
    }

    /**
     *非递归
     * @param arr 要进行查找的数组(数组是有序的)
     * @param target 要查找的目标数
     * @return 返回目标数的下标,没有找到返回-1
     */
    public static int binarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;
        while (left <= right){//如果left小于等于right就可以一直找
            int mid = (left + right) / 2;//数组中间值的下标
            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] < target){//向右查找
               left = mid + 1;
            }else{//向左查找
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     *
     * @param arr 目标数组
     * @param left 数组左边的索引
     * @param right 数组右边的索引
     * @param target 目标数
     * @return 找到返回目标数的下标,找不到返回-1
     */
    public static int binarySearch2(int[] arr,int left, int right,int target){
        //退出递归的条件
        if(left > right){
            return -1;
        }
        int mid = (left + right) / 2;//中间值的下标
        if(arr[mid] > target){//向左查找
            return binarySearch2(arr,left,mid - 1,target);
        }else if(arr[mid] < target){//向右查找
            return binarySearch2(arr,mid + 1,right,target);
        }else{
            return mid;
        }
    }
}
