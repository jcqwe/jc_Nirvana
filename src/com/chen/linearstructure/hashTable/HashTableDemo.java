package com.chen.linearstructure.hashTable;

import java.util.Scanner;

/**
 * 哈希表(散列函数)
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("add:添加员工");
            System.out.println("list:展示员工信息");
            System.out.println("find:查找员工信息");
            System.out.println("delete:根据员工id删除员工");
            System.out.println("exit:退出");
            String key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入员工id:");
                    int id = scanner.nextInt();
                    System.out.println("输入员工姓名:");
                    String name = scanner.next();
                    hashTab.add(new Emp(id, name));
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找员工的id");
                    id = scanner.nextInt();
                    System.out.println(hashTab.findEmpById(id));
                    break;
                case "delete":
                    System.out.println("请输入要删除员工的id");
                    id = scanner.nextInt();
                    hashTab.deleteEmpById(id);
                    break;
                case "exit":
                    flag = false;
                    scanner.close();
                    break;
            }
        }
    }
}

//hashtable
class HashTab {
    EmpLinkedList[] empLinkedListArray;
    int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //初始化每条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加员工到hashTab中
    public void add(Emp emp) {
        //确定员工添加在哪一条链表中
        int i = confirmById(emp.id);
        empLinkedListArray[i].add(emp);
    }

    //遍历整个链表数组
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }
    //根据员工id查找
    public Emp findEmpById(int id){
        //确定员工在哪一条链表
        int i = confirmById(id);
        Emp empById = empLinkedListArray[i].findEmpById(id);
        if(empById != null){
            return empById;
        }else {
            System.out.println("该hashTab中没有此员工");
            return null;
        }
    }
    //根据员工id删除该员工
    public void deleteEmpById(int id){
        //确定待删除的员工在哪一条链表中
        int i = confirmById(id);
        empLinkedListArray[i].deleteEmpById(id);
    }

    //根据员工id确认添加到那个链表
    public int confirmById(int id) {
        return id % size;
    }
}

//链表
class EmpLinkedList {
    private Emp head;//链表头指针默认为null

    //添加员工
    public void add(Emp emp) {
        //判断
        if (head == null) {
            head = emp;
            return;
        }
        //定义一个辅助变量
        Emp temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            //后移
            temp = temp.next;
        }
        //遍历完后找到要添加的位置
        temp.next = emp;
    }

    //遍历该链表
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + no + "条链表为null");
            return;
        }
        Emp temp = head;
        System.out.print("第" + no + "条链表信息");
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.print("=>id=" + temp.id + ";name=" + temp.name);
            //后移
            temp = temp.next;
        }
        System.out.println();
    }
    //查找
    public Emp findEmpById(int id){
        if(head == null){
            return null;
        }
        Emp temp = head;
        while (true){
            if(temp.id == id){
                break;
            }
            if(temp.next == null){
                temp = null;
                break;
            }
            //后移
            temp = temp.next;
        }
        return temp;
    }
    //删除
    public void deleteEmpById(int id){
        if(head == null){
            throw new RuntimeException("链表为null");
        }
        Emp temp = head.next;//指向后一个
        Emp preTemp = head;//指向前一个
        while (true){
            //如果待删除的元素是链表的头元素,直接置null，break
            if(head.id == id){
                head = null;
                break;
            }
            //找到待删除的元素
            if(temp.id == id){
                preTemp.next = temp.next;
                temp = null;
                break;
            }
            //没有找到待删除的元素
            if(temp == null){
                System.out.println("没有找到待删除的元素");
                break;
            }
            //一起后移
            temp = temp.next;
            preTemp = preTemp.next;
        }
    }
}

//员工
class Emp {
    public int id;//员工id
    public String name;//员工姓名
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", next=" + next +
                '}';
    }
}
