package com.chen.linearstructure.linkedList;

/**
 * 约瑟夫环
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addNode(25);
//        circleSingleLinkedList.show();

        circleSingleLinkedList.outOfQueue(1,2,25);
    }
}
//单项环形链表
class CircleSingleLinkedList{
    //创建一个头节点
    Node first;

    //添加角色到该环形链表中
    public void addNode(int nums){
        //数据校验
        if(nums < 1){
            System.out.println("加入的角色数量不能小于一个");
            return;
        }
        Node curNode = null;//辅助指针
        for (int i = 1; i <= nums; i++) {
            Node node = new Node(i);
            //判断是否是第一个角色
            if(i == 1){
                first = node;
                first.setNext(first);//构成环
                curNode = first;//curNode指向第一个节点
            }else {
                curNode.setNext(node);//让当前最后一个节点指向新加入的节点
                node.setNext(first);//新加入的节点指向第一个节点形成环状
            }
            //curNode后移
            curNode = curNode.getNext();
        }
    }
    //遍历环形链表
    public void show(){
        if(first == null){
            System.out.println("该环形链表没有节点");
            return;
        }
        Node curNode = first;
        while (true){
            System.out.println("加入节点的编号" + curNode.getNo());
            if(curNode.getNext() == first){
                break;
            }
            curNode = curNode.getNext();
        }
    }

    /**
     * @param startNo 从哪一个节点开始数数
     * @param countNum 数多少次出圈
     * @param nums 该圈共有几个节点
     */
    public void outOfQueue(int startNo, int countNum, int nums){
        //数据校验
        if(first == null || startNo < 1 || startNo > nums || countNum > nums){
            System.out.println("参数有误");
            return;
        }
        //辅助指针
        Node helper = first;
        //helper指向最后一个节点(first的前一个节点)
        while (true){
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //从哪一个节点开始数数,让first移动到该节点,helper也移动
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //出圈
        while (true){
            //当只剩一个节点时退出循环
            if(helper == first){
                break;
            }
            //数几次后出圈,first和helper移动
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println("出圈节点的编号" + first.getNo());
            //出圈操作
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后一个节点编号" + helper.getNo());
    }
}

//创建一个节点
class Node{
    private int no;//编号
    private Node next;//指向下一个节点

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node node) {
        this.next = node;
    }


}
