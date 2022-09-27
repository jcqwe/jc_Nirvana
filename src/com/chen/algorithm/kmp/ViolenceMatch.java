package com.chen.algorithm.kmp;

/**
 * 暴力匹配
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String s1 = "1234567213";
        String s2 = "213";
        System.out.println("index=" + violenceMatch(s1,s2));
    }

    public static int violenceMatch(String s1Str, String s2Str) {
        char[] s1 = s1Str.toCharArray();//s1Str对应的字符串数组
        char[] s2 = s2Str.toCharArray();//s2Str对应的字符串数组

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;//从s1的第一个元素开始
        int j = 0;//从s2的第一个元素开始
        while (i < s1Len && j < s2Len) {
            if (s1[i] == s2[j]) {//匹配上了
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if(j == s2Len){
            return i - j;
        }else{
            return -1;
        }
    }
}
