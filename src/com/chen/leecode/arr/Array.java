package com.chen.leecode.arr;

import java.lang.String;
import java.util.Stack;

/**
 * 1.两数相加
 * 2.两数之和
 */
public class Array {
    public static void main(String[] args) {
//        int[] nums = {2,7,11,15};
//        int target = 9;
//        System.out.println(Arrays.toString(sumByNum(nums,target)));
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;

        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(4);
        l4.next = l5;
        l5.next = l6;

        ListNode listNode = addTwoNumbers(l1, l4);
        while (true){
            System.out.print(listNode.val);
            listNode = listNode.next;
            if(listNode == null){
                break;
            }
        }
    }
    /*     两数之和
          给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
        你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
        你可以按任意顺序返回答案。
     */
    public static int[] sumByNum(int[] nums, int target){
        int[] res = new int[2];
        //以数组nums = {2,7,11,15};为例,想要找到答案直接的办法就是让数组中每个数都相加一边查看和的值
        //第一次循环2+7,2+11,2+15
        //第二次循环7+11,7+15
        //第三次循环11+15
        //分析可得三次循环中 左边数组的下标从0开始每次加1;右边的从3开始每次减1
        //当i = 0时 j = 3,2,1
        //i = 1时 j = 3,2
        //i = 2时 j = 3;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = nums.length - 1; j > i; j--) {
                if(nums[i] + nums[j] == target){
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }
    /*
                       两数相加
        给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
    请你将两个数相加，并以相同形式返回一个表示和的链表。
    你可以假设除了数字 0 之外，这两个数都不会以 0开头。
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //创建一个root结点
        ListNode root = new ListNode(0);
        ListNode temp = root;
        //表示进的位数,比如4 + 6 = 10 向前进一位
        int carry = 0;
        //考虑到两个结点是个位数时发生进位时加 || carry != 0
        while (l1 != null || l2 != null || carry != 0){
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            carry = sumVal / 10;
            //把两个结点的和生成一个新的结点
            ListNode sumNode = new ListNode(sumVal % 10);
            //让头节点指向新生成的结点
            temp.next = sumNode;
            //后移
            temp = temp.next;
            //两个结点后移
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        return root.next;
    }

}
class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
