package com.chen.linearstructure.stack;

import com.sun.org.apache.xpath.internal.operations.Div;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //完成将一个中缀表达式转成后缀表达式的功能
        //说明
        //1. 1+((2+3)×4)-5 => 转成  1 2 3 + 4 × + 5 –
        //2. 因为直接对str 进行操作，不方便，因此 先将  "1+((2+3)×4)-5" =》 中缀的表达式对应的List
        //   即 "1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //3. 将得到的中缀表达式对应的List => 后缀表达式对应的List
        //   即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  =》 ArrayList [1,2,3,+,4,*,+,5,–]
        String expression = "1+((2+3)*4)-5";
        List<String> toInfixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式=" + toInfixExpressionList);

        List<String> postfixExpressionList = toPostfixExpressionList(toInfixExpressionList);
        System.out.println("后缀表达式=" + postfixExpressionList);
        int res = calculate(postfixExpressionList);
        System.out.println("res=" + res);
        //先定义给逆波兰表达式
        //(3+4)*5-6 -> 3 4 + 5 * 6 -;
//        String expression = "3 4 + 5 * 6 -";
//        /*
//        1.先将3 4 + 5 * 6 -放入到ArrayList中
//        2.遍历list配和栈完成计算
//         */
//        List<String> list = getListString(expression);
//        System.out.println(list);
//
//        int res = calculate(list);
//        System.out.println("结果为=" + res);
    }

    //将中缀表达式存入List;s = "1+((2+3)*4)-5"
    public static List<String> toInfixExpressionList(String s) {
        List<String> list = new ArrayList<String>();
        int i = 0;//用于遍历表达式
        String str;//用于处理多位数
        char c;//遍历的每个字符都放入到c
        do {
            //如果c是一个非数字,直接加入到list中
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add("" + c);
                i++;//i后移
            } else {
                //如果c是一个数字的话,考虑多位数
                str = "";//先把str置成""
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    //拼接多位数
                    str += c;
                    //后移
                    i++;
                }
                list.add(str);
            }
        } while (i < s.length());
        return list;
    }

    //将list中的中缀表达式转换成后缀表达式
    public static List<String> toPostfixExpressionList(List<String> list) {
        //初始化两个栈s1(存放运算符)和s2(存放运算结果)
        Stack<String> s1 = new Stack<>();
        //因为s2只有入栈操作没有出栈操作所以s2可以用ArrayList代替
        List<String> s2 = new ArrayList<String>();
        //遍历存放中缀表达式的list
        for (String item : list) {
            //如果该字符是数字的话就直接加入到s2中
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {//如果字符是左括号的话,直接将该运算符压入到s1栈中
                s1.push(item);
            } else if (item.equals(")")) {//如果字符是右括号的话,则依次弹出s1栈顶的运算符加入到s2中,直到s1栈顶元素为左括时,将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                //若item的优先级小于等于s1栈顶运算符,将s1栈顶的运算符加入到s2中,再次转到4.1与新的s1的栈顶运算符优先级进行比较
                while (s1.size() != 0 && Operation.priority(s1.peek()) >= Operation.priority(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        //将s1中的运算符加入到s2中
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    //将逆波兰表达式存入list中
    public static List<String> getListString(String expression) {
        String[] split = expression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String str : list) {
            //使用正则表达式判断
            if (str.matches("\\d+")) {//\\d+匹配0-9的数字
                stack.push(str);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (str.equals("+")) {
                    res = num1 + num2;
                } else if (str.equals("-")) {
                    res = num1 - num2;
                } else if (str.equals("*")) {
                    res = num1 * num2;
                } else if (str.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误..");
                }
                //将结果入栈
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }

}

class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int priority(String operation) {
        int res = 0;
        switch (operation) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return res;
    }
}
