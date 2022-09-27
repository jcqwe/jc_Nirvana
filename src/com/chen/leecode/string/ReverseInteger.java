package com.chen.leecode.string;

/**
 * 整数反转
 */
public class ReverseInteger {
    /*
        给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
如果反转后整数超过 32 位的有符号整数的范围[−2^31, 2^31− 1] ，就返回 0。
假设环境不允许存储 64 位整数（有符号或无符号）。
        示例 1：

            输入：x = 123
            输出：321
        示例 2：

            输入：x = -123
            输出：-321
        示例 3：

            输入：x = 120
            输出：21
        示例 4：

            输入：x = 0
            输出：0
     */
    public static void main(String[] args){
        System.out.println(reverse(1234444444));
    }
    public static int reverse(int x) {
       int res = 0;
       while (x != 0){
           int t = x % 10;
           int newRes = res * 10 + t;
           //如果数字溢出直接返回0
           if((newRes - t) / 10 != res){
               return 0;
           }
           res = newRes;
           x = x / 10;
       }
       return res;
    }
}
