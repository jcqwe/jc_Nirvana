package com.chen.linearstructure.linkedList;

import java.util.Stack;

//单链表
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //创建一个单链表
        SingleLinkedList list1 = new SingleLinkedList();
        SingleLinkedList list2 = new SingleLinkedList();
        SingleLinkedList list3 = new SingleLinkedList();
        //创建节点
        HeroNode hero1 = new HeroNode(1, "温良", "单手剑");
        HeroNode hero2 = new HeroNode(2, "楚休狂", "天刀");
        HeroNode hero3 = new HeroNode(7, "清虚", "太乙");
        HeroNode hero4 = new HeroNode(4, "玄悲", "少林");
        HeroNode hero5 = new HeroNode(5, "周清澜", "泠月");

        HeroNode hero6 = new HeroNode(6, "温良", "单手剑");
        HeroNode hero7 = new HeroNode(4, "楚休狂", "天刀");
        list2.test2(hero6);
        list2.test2(hero7);
        //添加:不考虑编号
//        list.add(hero1);
//        list.add(hero2);
//        list.add(hero3);
//        list.add(hero4);
//        list.addByHeroNum(hero1);
//        list.addByHeroNum(hero3);
//        list.addByHeroNum(hero2);
//        list.addByHeroNum(hero4);
//        list.addByHeroNum(hero5);
//        list.test(hero1);
//        list.test(hero3);
//        list.test(hero2);
//        list.test(hero4);
//        list.test(hero5);
        list1.test2(hero1);
        list1.test2(hero3);
        list1.test2(hero2);
        list1.test2(hero4);
        list1.test2(hero5);

        //合并两个单链表
        HeroNode heroNode = mergeLinkedList(list1.head, list2.head);

        list3.showList(heroNode);
//        System.out.println("原单链表:");
//        list.show();
//        System.out.println("反转后");
////        reverseNode2(list.head);
//        reverseNode(list.head);
//        list.show();
        //逆序打印单链表,不改变原单链表的结构
//        System.out.println("逆序打印:");
//        reversePrint(list.head);
//        list.test2(hero5);
//        list.show();
//        HeroNode newHeroNode = new HeroNode(5, "小周", "泠月--");
//        list.update(newHeroNode);
//        System.out.println("修改后");
//        list.show();
//
//        list.del(1);
//        list.del(5);
//        list.del(2);
//        System.out.println("删除后链表节点情况:");
//        list.show();


        //显示
//        list.showList();
//        System.out.println("单链表中有效节点的个数:" + count(list.head));
//
//        System.out.println("倒数第k个节点是:" + getNode(list.head,2));
    }

    //单链表中有效节点的个数(带头节点的链表,要排除头节点)
    public static int count(HeroNode head) {
        //判断链表是否为空
        if (head.next == null) {
            return 0;
        }
        HeroNode temp = head;
        int length = 0;
        //遍历单链表
        while (temp.next != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    //查找单链表中倒数第K个节点
    public static HeroNode getNode(HeroNode head, int index) {
        //判断链表是否为空
        if (head.next == null) {
            return null;
        }
        //第一次遍历,找到该链表中节点的个数
        int size = count(head);
        HeroNode temp = head.next;
        //第二次遍历,找到倒数第K个节点
        //数据校验
        if (index <= 0 || index > size) {
            return null;
        }
        for (int i = 0; i < (size - index); i++) {
            //后移
            temp = temp.next;
        }
        return temp;
    }

    //单链表的反转
    public static void reverseNode(HeroNode head) {
        //判断该链表是否为null和是否只有一个节点
        if (head.next == null || head.next.next == null) {
            return;
        }
        //创建一个新的头节点
        HeroNode reverseNode = new HeroNode(0, "", "");
        HeroNode cur = head.next;
        HeroNode temp;
        //遍历原单链表
        while (cur != null) {
            //取出原单链表的第x个节点
            temp = cur;
            //cur后移
            cur = cur.next;
            //将取出的节点的下一个指向新链表的头节点
            temp.next = reverseNode.next;
            //新链表的头节点指向取出的原链表的节点
            reverseNode.next = temp;
        }
        head.next = reverseNode.next;
    }

    //单链表的反转02
    public static void reverseNode2(HeroNode head) {
        //判断该链表是否为null和是否只有一个节点
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode beg = null;
        HeroNode mid = head.next;
        HeroNode end = head.next.next;
        //遍历原单链表
        while (true) {
            mid.next = beg;
            if (end == null) {
                break;
            }
            beg = mid;
            mid = end;
            end = end.next;
        }
        head.next = mid;
    }

    //逆序打印单链表,不改变原单链表的结构
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        while (temp != null) {
            //将单链表的各个节点存入栈中
            stack.push(temp);
            //后移
            temp = temp.next;
        }
        //从栈中取出节点
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /*
    合并两个有序的链表,合并之后链表依然有序
    1.
     */
    public static HeroNode mergeLinkedList(HeroNode head1, HeroNode head2) {
        //创建一个新的头节点
        HeroNode newNode = new HeroNode(0,"","");
        //新的头节点不变,赋值给一个变量temp
        HeroNode temp = newNode;
        //h1,h2分别代表两个单链表的第一个有效节点;
        HeroNode h1 = head1.next;
        HeroNode h2 = head2.next;
        //循环遍历直到有一个链表为空
        while (h1 != null && h2 !=null){
            //比较h1和h2的编号
            //把temp.next指向小的节点,然后将temp后移
            if(h1.num <= h2.num){
                temp.next = h1;
                h1 = h1.next;
            }else{
                temp.next = h2;
                h2 = h2.next;
            }
            temp = temp.next;
        }
        //退出循环时,必然有一个单链表为空,将temp.next指向剩余的不为空的单链表
        temp.next = h1 == null ? h2 : h1;
        //返回
        return newNode;
    }
}


//管理单链表
class SingleLinkedList {
    public HeroNode head = new HeroNode(0, "", "");//头结点,不存放数据

    //添加数据
    //不考虑英雄的编号
    public void add(HeroNode hero) {
        HeroNode temp = head;
        while (true) {
            //判断最后一个节点,next是否为空
            if (temp.next == null) {
                break;
            }
            //不为空,temp往后移
            temp = temp.next;
        }
        temp.next = hero;
    }

    //不考虑编号
    public void test(HeroNode node) {
        //头节点不变
        HeroNode temp = head;
        while (true) {
            //找到单链表中最后一个元素(及next==null)
            if (temp.next == null) {
                break;
            }
            //否则temp后移
            temp = temp.next;
        }
        temp.next = node;
    }

    //根据编号插入
    public void test2(HeroNode node) {
        boolean flag = false;
        //头节点不变
        HeroNode temp = head;
        while (true) {
            //找到单链表中最后一个元素(及next==null)
            if (temp.next == null) {
                break;
            }
            //根据新节点的编号找到对应的位置
            if (temp.next.num > node.num) {
                break;
            } else if (temp.next.num == node.num) {
                flag = true;
                break;
            }
            //否则temp后移
            temp = temp.next;
        }
        if (flag) {
            System.out.println("新节点和已存在节点编号冲突..");
            return;
        } else {
            node.next = temp.next;
            temp.next = node;
        }
    }

    //展示链表
    public void show() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空..");
            return;
        }
        HeroNode temp = head.next;
        //遍历单链表
        while (true) {
            //找到最后一个元素
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //修改链表
    public void update(HeroNode newHeroNode) {
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            //判断链表是否遍历完
            if (temp == null) {
                break;
            }
            //找到要修改节点的编号
            if (temp.num == newHeroNode.num) {
                flag = true;
                break;
            }
            //后移
            temp = temp.next;
        }
        //找到
        if (flag) {
            temp.name = newHeroNode.name;
            temp.alias = newHeroNode.alias;
        } else {
            System.out.println("该节点不存在,请先添加..");
        }
    }

    //删除链表中的节点
    public void del(int num) {
        HeroNode temp = head;
        boolean flag = false;
        //遍历链表
        while (true) {
            if (temp.next == null) {
                break;
            }
            //找到需要删除的节点
            if (temp.next.num == num) {
                flag = true;
                break;
            }
            //后移
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("没有该节点,请先添加.");
        }
    }

    //根据英雄的编号添加
    public void addByHeroNum(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            //找到最后一个节点
            if (temp.next == null) {
                break;
            }
            //如果最后一个英雄编号大于新英雄的编号,说明新英雄位置已经找到
            if (temp.next.num > heroNode.num) {
                break;
            } else if (temp.next.num == heroNode.num) {
                //该排名已存在
                flag = true;
                break;
            }
            //temp后移
            temp = temp.next;
        }
        //如果flag为true:排名已存在
        if (flag) {
            System.out.println("该排名已存在.");
            return;
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //根据传入的节点遍历展示链表
    public void showList(HeroNode newNode) {
        //判断链表是否为null
        if (newNode.next == null) {
            System.out.println("链表为空.");
            return;
        }
        HeroNode temp = newNode.next;
        //遍历
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            //temp往后移
            temp = temp.next;
        }
    }
}

//节点
class HeroNode {
    public int num;//编号
    public String name;//名字
    public String alias;//别名

    public HeroNode next;//指向下一个节点

    public HeroNode(int num, String name, String alias) {
        this.num = num;
        this.name = name;
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}
