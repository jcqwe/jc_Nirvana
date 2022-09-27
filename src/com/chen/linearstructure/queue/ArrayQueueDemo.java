package com.chen.linearstructure.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        ArrayQueue arrayQueue = new ArrayQueue(4);
        ArrayCircleQueue arrayCircleQueue = new ArrayCircleQueue(4);
        while (flag) {
            System.out.println("从队列中取出数据(1):");
            System.out.println("添加数据(2):");
            System.out.println("查看队列中所有元素(3):");
            System.out.println("查看队列中所有有效元素(4):");
            System.out.println("查看队列中有效数据的个数(5):");
            System.out.println("退出(6):");
            int i = scanner.nextInt();
            switch (i) {
                case 1:
                    try {
                        int numByQueue = arrayCircleQueue.getNumByQueue();
                        System.out.println("取出:" + numByQueue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("请输入要添加的数据:");
                        int addNum = scanner.nextInt();
                        arrayCircleQueue.addQueue(addNum);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    int[] arrs = arrayCircleQueue.getArrs();
                    for (int j = 0; j < arrs.length; j++) {
                        System.out.println("arrs[" + j + "]=" + arrs[j]);
                    }
                    break;
                case 4:
                    arrayCircleQueue.effective();
                    break;
                case 5:
                    System.out.println("队列中有效数据个数为:" + arrayCircleQueue.effectiveNum());
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    System.out.println("请输入有效数字");
                    break;
            }
        }
        System.out.println("退出程序..");
    }
}

//数组模拟队列
class ArrayQueue {
    private int maxSize;//数组大小
    private int rear;//尾指针
    private int front;//头指针

    private int[] arrs;

    //初始化
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        rear = -1;
        front = -1;
        arrs = new int[maxSize];
    }

    //添加数据到队列
    public void addQueue(int num) {
        //先判断队列是否满
        if (rear == maxSize - 1) {
            throw new RuntimeException("队列已满,无法添加.");
        }
        arrs[++rear] = num;
    }

    //从队列中取出数据
    public int getNumByQueue() {
        //先判断队列是否为空
        if (rear == front) {
            throw new RuntimeException("队列为空,请先添加数据");
        }
        ++front;
        int num = arrs[front];
        arrs[front] = 0;
        return num;
    }

    public int[] getArrs() {
        return arrs;
    }
}

//数组模拟环形队列
class ArrayCircleQueue {
    private int maxSize;//数组大小
    private int rear;//队尾(不存放元素,预留一个空间).初始值为0
    private int front;//队头(指向队列第一个元素).初始值为0

    private int[] arrs;

    //初始化
    public ArrayCircleQueue(int maxSize) {
        this.maxSize = maxSize;
        arrs = new int[maxSize];
    }

    //添加数据到队列
    public void addQueue(int num) {
        //先判断队列是否满
        if ((rear + 1) % maxSize == front) {
            throw new RuntimeException("队列已满,无法添加.");
        }
        arrs[rear] = num;
        rear = (rear + 1) % maxSize;
    }

    //从队列中取出数据
    public int getNumByQueue() {
        //先判断队列是否为空
        if (rear == front) {
            throw new RuntimeException("队列为空,请先添加数据");
        }
        int num = arrs[front];
        arrs[front] = 0;
        front = (front + 1) % maxSize;
        return num;
    }

    //环形队列中有效数据
    public void effective() {
        for (int i = front; i < front + effectiveNum(); i++) {
            System.out.println("arr[" + i % maxSize + "]=" + arrs[i % maxSize]);
        }
    }

    //环形队列中的有效数据的个数
    public int effectiveNum() {
        return (rear - front + maxSize) % maxSize;
    }

    public int[] getArrs() {
        return arrs;
    }
}

