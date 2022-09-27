package com.chen.leecode.string;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 无重复字符的最长子串
 */
public class NoRepetitionString {
    /*
    给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
        示例1:
            输入: s = "abcabcbb"
            输出: 3
            解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
        示例 2:
            输入: s = "bbbbb"
            输出: 1
            解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
        示例 3:
            输入: s = "pwwkew"
            输出: 3
    解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
s 由英文字母、数字、符号和空格组成
     */
    public static void main(String[] args) {
        String s = "";
        int i = lengthOfLongestSubstring(s);
        System.out.println(i);
    }

    public static int lengthOfLongestSubstring(String s) {
        //当s长度为1时直接返回1
        if (s.length() == 1) {
            return 1;
        }
        char[] chars = s.toCharArray();
        //ch的key存放字符,value存放字符对应的下标
        HashMap<Character, Integer> ch = new HashMap<>();
        //存放子串序列的长度
        ArrayList<Integer> res = new ArrayList<>();
        int i = 0;
        //记录不重复子串的长度
        int count = 0;
        while (i < chars.length) {
            //如果s中有重复的元素,就记录一次子串长度,将集合置空开始下次循环
            if (ch.containsKey(chars[i])) {
                res.add(count);
                Integer index = ch.get(chars[i]);
                i = i - index == 1 ? i : index + 1;
                count = 0;
                //将ch置空
                ch.clear();
                continue;
            }
            ch.put(chars[i], i);
            count++;
            i++;
        }
        int max = 0;
        if(res.size() > 0){
            for (Integer re : res) {
                if (re > max) {
                    max = re;
                }
            }
        }
        return max > count ? max : count;
    }
}
