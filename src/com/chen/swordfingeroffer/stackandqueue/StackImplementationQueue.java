package com.chen.swordfingeroffer.stackandqueue;

import java.util.Stack;

/**
 * 用两个栈实现队列
 */
public class StackImplementationQueue {
    /*
    用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead操作返回 -1 )
        示例 1：
            输入：
            ["CQueue","appendTail","deleteHead","deleteHead"]
            [[],[3],[],[]]
            输出：[null,null,3,-1]
        示例 2：
            输入：
            ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
            [[],[],[5],[2],[],[]]
            输出：[null,-1,null,null,5,2]
     */
    public static void main(String[] args){

    }
}
class CQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public CQueue() {
        stack1 = new Stack();
        stack2 = new Stack();
    }
    //队列中添加元素
    public void appendTail(int value) {
        stack1.add(value);
    }

    public int deleteHead() {

        return -1;
    }
}
