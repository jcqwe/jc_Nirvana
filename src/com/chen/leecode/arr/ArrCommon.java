package com.chen.leecode.arr;

import java.util.*;

/**
 * 求两个数组的交集
 */
public class ArrCommon {
    /*
    示例:
        输入：nums1 = [1,2,2,1], nums2 = [2,2]
        输出：[2,2]

        输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
        输出：[4,9]
     */
    public static void main(String[] args) {
        int[] nums1 = {4,4,9,5};
        int[] nums2 = {9,4,9,8,4};
        int[] res = res(nums1, nums2);
        System.out.println(Arrays.toString(res));
//        Map<Integer,Integer> map = new HashMap<>();
//        map.put(1,map.getOrDefault(1,0) + 1);
//        map.put(1,map.getOrDefault(1,0) + 1);
//        map.put(1,map.getOrDefault(1,0) + 1);
//        map.put(1,map.getOrDefault(1,0) - 1);
//        System.out.println(map);
    }

    public static int[] res(int[] nums1, int[] nums2) {
        //创建一个hashmap集合key存放nums1的元素,value存放元素在nums1中出现的次数
        Map<Integer,Integer> map = new HashMap<>();
        ArrayList<Integer> resList = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i],map.getOrDefault(nums1[i],0) + 1);
        }
        for (int i = 0; i < nums2.length; i++) {
            if(map.getOrDefault(nums2[i], 0) > 0){
                resList.add(nums2[i]);
                map.put(nums2[i], map.put(nums2[i],0) - 1);
            }
        }
        int[] res = new int[resList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = resList.get(i);
        }
        return res;
    }
}
