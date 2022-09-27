package com.chen.linearstructure.linkedList;

import java.util.Stack;

/**
 * 栈:先进后出
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.add("jc");
        stack.add("jc01");
        stack.add("jc02");

        //取出
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
}
