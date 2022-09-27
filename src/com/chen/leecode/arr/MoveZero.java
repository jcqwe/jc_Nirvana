package com.chen.leecode.arr;

import java.util.Arrays;

/**
 * 移动零
 */
public class MoveZero {
    /*
        给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
        请注意，必须在不复制数组的情况下原地对数组进行操作。



        示例 1:
            输入: nums = [0,1,0,3,12]
            输出: [1,3,12,0,0]
        示例 2:
            输入: nums = [0]
            输出: [0]
     */
    public static void main(String[] args) {
//        int[] nums = {0,1,0,3,12};
        int[] nums = {0};
        move(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void move(int[] nums) {
        int i = 0;
        int temp;
        for (int j = 0; j < nums.length; j++) {
            //如果nums[j]!=0就往前移
            if (nums[j] != 0) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
    }
}
