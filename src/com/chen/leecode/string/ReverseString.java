package com.chen.leecode.string;

import java.util.Arrays;
import java.util.Stack;

/**
 * 字符串反转
 */
public class ReverseString {
    /*
        编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
        示例 1：

            输入：s = ["h","e","l","l","o"]
            输出：["o","l","l","e","h"]
        示例 2：

            输入：s = ["H","a","n","n","a","h"]
            输出：["h","a","n","n","a","H"]
     */
    public static void main(String[] args) {
        char[] s = {'h','e','l','l','o'};
//        reverse(s);
        reverse2(s);
        System.out.println(Arrays.toString(s));
    }
    //利用栈的特点先进后出
    public static void reverse(char[] s){
        Stack<Character> stack = new Stack<>();
        for (char c : s) {
            stack.push(c);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            Character pop = stack.pop();
            stringBuilder.append(pop);
        }
        char[] res = stringBuilder.toString().toCharArray();
        for (int i = 0; i < s.length; i++) {
            s[i] = res[i];
        }
    }
    //对称交换
    public static void reverse2(char[] s){
        int left = 0;
        int right = s.length - 1;
        char temp;
        while (left < right){
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
