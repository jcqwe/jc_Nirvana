package com.chen.leecode.arr;

import java.util.Arrays;

/**
 * 加一
 */
public class AddOne {
    /*
        示例1：
            输入：digits = [1,2,3]
            输出：[1,2,4]
            解释：输入数组表示数字 123。
        示例2：
            输入：digits = [4,3,2,1]
            输出：[4,3,2,2]
            解释：输入数组表示数字 4321。
        示例3：
            输入：digits = [0]
            输出：[1]

     */
    public static void main(String[] args) {
        int[] digits = {1,9,9};
        int[] res = res(digits);
        System.out.println(Arrays.toString(res));
    }
    public static int[] res(int[] digits){
        int len = digits.length;
        for (int i = digits.length - 1; i >= 0; i--) {
            //如果数组最后一个元素不是9直接++返回
            if(digits[i] != 9){
                digits[i]++;
                return digits;
            }else {//如果数组最后一个元素是9,则最后一个元素置0,下一个元素++返回
                digits[i] = 0;
            }
        }
        //如果遍历完后没有返回就说明该数组全是9,
        int[] re = new int[len+1];
        re[0] = 1;
        return re;
    }
}
