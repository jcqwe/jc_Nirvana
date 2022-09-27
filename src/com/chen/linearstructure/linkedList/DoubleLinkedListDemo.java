package com.chen.linearstructure.linkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //创建一个双向链表
        DoubleLinkedList dlist = new DoubleLinkedList();

        HeroNode2 hero1 = new HeroNode2(1, "温良", "单手剑");
        HeroNode2 hero2 = new HeroNode2(2, "楚休狂", "天刀");
//        HeroNode2 hero3 = new HeroNode2(3, "清虚", "太乙");
        HeroNode2 hero4 = new HeroNode2(4, "玄悲", "少林");
        HeroNode2 hero5 = new HeroNode2(5, "周清澜", "泠月");


        HeroNode2 hero6 = new HeroNode2(3, "玄悲", "少林");
        HeroNode2 hero7 = new HeroNode2(6, "周清澜", "泠月");


        //添加(默认添加到链表最后)
        dlist.add(hero1);
        dlist.add(hero2);
//        dlist.add(hero3);
        dlist.add(hero4);
        dlist.add(hero5);
        System.out.println("添加后的双向链表");
        dlist.show();
        //修改
        HeroNode2 newNode = new HeroNode2(4, "云来", "养猪");
        dlist.update(newNode);
        System.out.println("修改后的双向链表");
        dlist.show();
        //删除
        dlist.del(5);
        System.out.println("删除后的链表情况");
        dlist.show();
        //添加(根据编号)
        dlist.add2(hero6);
        dlist.add2(hero7);
        System.out.println("添加后");
        dlist.show();
    }
}

class DoubleLinkedList {
    //初始化一个头节点
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }
    //遍历双向链表
    public void show() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空..");
            return;
        }
        HeroNode2 temp = head.next;
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
    //添加节点到双向链表(默认添加到最后)
    public void add(HeroNode2 hero) {
        HeroNode2 temp = head;
        while (true) {
            //判断最后一个节点,next是否为空
            if (temp.next == null) {
                break;
            }
            //不为空,temp往后移
            temp = temp.next;
        }
        temp.next = hero;
        hero.pre = temp;
    }
    //修改双向链表
    public void update(HeroNode2 newHeroNode) {
        HeroNode2 temp = head.next;
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
    //根据编号删除双向链表中的节点
    public void del(int num) {
        HeroNode2 temp = head.next;
        //判断是否找到该节点
        boolean flag = false;
        //遍历链表
        while (true) {
            if (temp == null) {
                break;
            }
            //找到需要删除的节点
            if (temp.num == num) {
                flag = true;
                break;
            }
            //后移
            temp = temp.next;
        }
        if (flag) {
            //待删除的节点的前一个节点的next指向待删除节点的后一个节点
            temp.pre.next = temp.next;
            //判断待删除节点是否是最后一个节点
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("没有该节点,请先添加.");
        }
    }
    //根据编号添加
    public void add2(HeroNode2 node) {
        boolean flag = false;
        //头节点不变
        HeroNode2 temp = head;
        while (true) {
            //找到链表中最后一个元素(及next==null)
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
            if(temp.next == null){
                node.pre = temp;
                node.next = temp.next;
                temp.next = node;
            } else{
                node.pre = temp;
                node.next = temp.next;
                temp.next = node;
                temp.next.pre = node;
            }
        }
    }
}

//节点
class HeroNode2 {
    public int num;//编号
    public String name;//名字
    public String alias;//别名

    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre;//指向前一个节点

    public HeroNode2(int num, String name, String alias) {
        this.num = num;
        this.name = name;
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}
