package com.chen.linearstructure.stack;

import java.util.Scanner;

/**
 * 数组模拟栈
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        String key = "";
        while (loop){
            System.out.println("show 展示栈中的数据");
            System.out.println("push 入栈");
            System.out.println("pop 出栈");
            System.out.println("exit 退出");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入要加入的数据:");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        System.out.println("从栈中取出数据" + stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    loop = false;
                    break;
            }
        }
        System.out.println("退出程序..");
    }
}

class ArrayStack {
    private int maxSize;//栈的大小
    private int top = -1;//栈顶
    private int[] stack;//栈(数组模拟栈)

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value){
        //判断栈是否满
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        stack[++top] = value;
    }
    //出栈
    public int pop(){
        //判断栈是否为空
        if(isEmpty()){
            throw new RuntimeException("栈空...");
        }
        int value = stack[top--];
        return value;
    }
    //展示栈中的数据(从栈顶开始显示)
    public void list(){
        //判断栈是否为空
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println("stack["+ i + "]=" + stack[i]);
        }
    }
}
//单向链表模拟栈
class LinkedListStack{

}
