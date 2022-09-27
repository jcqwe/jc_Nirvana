package com.chen.linearstructure.linkedList;

import java.util.Stack;

public class Test {
    public static void main(String[] args) {
        //字符串反转
        String s = "abcedfg";
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char aChar : chars) {
            stack.push(aChar);
        }
        StringBuffer stringBuffer = new StringBuffer();
        //遍历
        while (stack.size() > 0){
            Character character = stack.pop();
            stringBuffer.append(character);
        }
        System.out.println(stringBuffer);
    }
}
