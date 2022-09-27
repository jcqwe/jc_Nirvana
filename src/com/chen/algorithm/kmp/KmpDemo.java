package com.chen.algorithm.kmp;

import java.util.Arrays;

/**
 * 使用KMP算法解决字符串匹配问题
 */
public class KmpDemo {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";//0,0,0,0,1,2,0
        int[] kmpNext = getKmpNext(str2);
        System.out.println(Arrays.toString(kmpNext));
        int i = kmpSearch(str1, str2, kmpNext);
        System.out.println("index=" + i);
    }

    /**
     * @param str1 原字符串
     * @param str2 子串
     * @param next 子串的部分匹配表
     * @return 找到就返回子串第一个字符在原字符串中的下标, 找不到返回-1
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    //得到字符串(子串)的部分匹配表
    //ABCDABD
    public static int[] getKmpNext(String str) {
        int[] next = new int[str.length()];
        next[0] = 0;//str只有一个元素
        for (int i = 1, j = 0; i < str.length(); i++) {
            //如果满足j > 0 && str.charAt(i) != str.charAt(j),就让j向前一位继续匹配
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j - 1];
            }
            //如果满足str.charAt(i) == str.charAt(j),就让部分匹配值+1
            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
