package com.chen.leecode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 字符串中的第一个唯一字符
 */
public class OnlyOneString {
    public static void main(String[] args) {
        /*
            给定一个字符串s，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1。
            示例 1：
                输入: s = "leetcode"
                输出: 0
            示例 2:
                输入: s = "loveleetcode"
                输出: 2
            示例 3:
                输入: s = "aabb"
                输出: -1
         */
//        String s = "aabb";
//        int only = findOnly(s);
//        System.out.println(only);
//        String s1 = "A man, a plan, a canal: Panama";
//        String s1 = "OP";
//        boolean b = checkString(s1);
//        System.out.println(b);
        String haystack = "mississippi";
        String needle = "issip";
//        int i = matchingString(haystack, needle);
//        System.out.println(i);
        int[] matchNext = getMatchNext(needle);
        int i1 = kmpSearch(haystack, needle, matchNext);
        System.out.println(i1);
    }

    public static int findOnly(String s) {
        int[] count = new int[26];
        char[] chars = s.toCharArray();
        //统计每个字符出现的次数
        for (int i = 0; i < chars.length; i++) {
            count[chars[i] - 'a']++;
        }
        //遍历s
        for (int i = 0; i < chars.length; i++) {
            if (count[chars[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
/*
给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
注意：若s 和 t中每个字符出现的次数都相同，则称s 和 t互为字母异位词。
        示例1:
            输入: s = "anagram", t = "nagaram"
            输出: true
        示例 2:
            输入: s = "rat", t = "car"
            输出: false
 */
    public static boolean ectopic(String s, String t) {
        int[] scount = new int[26];
        int[] tcount = new int[26];
        char[] sch = s.toCharArray();
        char[] tch = t.toCharArray();
        //计算每个字母出现的次数
        for (int i = 0; i < sch.length; i++) {
            scount[sch[i] - 'a']++;
        }
        for (int i = 0; i < tch.length; i++) {
            tcount[tch[i] - 'a']++;
        }
        boolean res = false;
        //比较s和t中26个字母出现的次数
        for (int i = 0; i < scount.length; i++) {
            //如果有一个不相等直接返回false
            if (scount[i] != tcount[i]) {
                return false;
            }else{
                res = true;
            }
        }
        return res;
    }
    /*
    验证回文串
        给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
        说明：本题中，我们将空字符串定义为有效的回文串。
            示例 1:
            输入: "A man, a plan, a canal: Panama"
            输出: true
            解释："amanaplanacanalpanama" 是回文串
            示例 2:
            输入: "race a car"
            输出: false
            解释："raceacar" 不是回文串
     */
    public static boolean checkString(String s){
        char[] schars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        //得到s中的字母和数字
        for (int i = 0; i < schars.length; i++) {
            if((schars[i] >= 'a' && schars[i] <= 'a' + 25) || (schars[i] >= 'A' && schars[i] <= 'A' + 25) || (schars[i] >= 48 && schars[i] <= 57)){
                stringBuilder.append(schars[i]);
            }
        }
        //将s中的字母全部转为小写
        String lowerCase = stringBuilder.toString().toLowerCase();
        char[] lowerCaseChars = lowerCase.toCharArray();
        int left = 0;
        int right = lowerCaseChars.length - 1;
        //前后比较字母是否相同,如果相同左右指针分别移动,否则直接返回false
        while (left < right){
            if(lowerCaseChars[left] == lowerCaseChars[right]){
                left++;
                right--;
            }else {
                return false;
            }
        }
        return true;
    }
    /*
  实现strStr()函数。
给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
说明：
    当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
对于本题而言，当needle是空字符串时我们应当返回 0 。这与 C 语言的strstr()以及 Java 的indexOf()定义相符。
        示例 1：
            输入：haystack = "hello", needle = "ll"
            输出：2
        示例 2：
            输入：haystack = "aaaaa", needle = "bba"
            输出：-1
        示例 3：
            输入：haystack = "", needle = ""
            输出：0
     */
    public static int matchingString(String haystack,String  needle){
        //如果haystack或needle为空串直接返回0
        if((needle.length() == 0 && haystack.length() != 0) || (needle.length() == 0 && haystack.length() == 0)){
            return 0;
        }
        if((haystack.length() == 0 && needle.length() != 0) || haystack.length() < needle.length()){
            return -1;
        }
        char[] hc = haystack.toCharArray();
        char[] nc = needle.toCharArray();
        int i = 0;
        int j = 0;
        ArrayList<Integer> res = new ArrayList<>();
        boolean flag = false;
        //进入hc第一次循环,后把i赋值给temp,进入nc的循环遍历,比较第一个值是否相等,如果相等,hc后移,nc后移继续比较,如果不相等退出,hc后移进行下一次比较
        while (i < hc.length){
            int temp = i;
            while (j < nc.length){
                if(hc[i] == nc[j]){
                    res.add(i);
                    i++;
                    j++;
                    flag = true;
                }else{
                    j = 0;
                    flag = false;
                    break;
                }
            }
            if(flag){
                if(res.size() == needle.length()){
                    return res.get(0);
                }else{
                    return res.get(res.size() - needle.length());
                }
            }else{
                i = temp + 1;
            }
        }
        return -1;
    }
    //得到字符串的部分匹配表
    public static int[] getMatchNext(String str){
        int[] next = new int[str.length()];
        char[] strCh = str.toCharArray();
        //字符串的第一个元素匹配值为0
        next[0] = 0;
        for (int i = 1, j = 0; i < strCh.length; i++) {
            //如果j大于0并且strCh[i] != strCh[j] 让j往前移一位继续匹配
            while (j > 0 && strCh[i] != strCh[j]){
                j = next[j - 1];
            }
            //如果后一个元素和第一个元素相等,则匹配值+1
            if(strCh[i] == strCh[j]){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
    /**
     * @param str1 原字符串
     * @param str2 子串
     * @param next 子串的部分匹配表
     * @return 找到就返回子串第一个字符在原字符串中的下标, 找不到返回-1
     */
    //"mississippi";
    //"issip";
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
}
