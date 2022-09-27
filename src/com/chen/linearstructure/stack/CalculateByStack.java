package com.chen.linearstructure.stack;

/**
 * 通过栈计算表达式
 */
public class CalculateByStack {
    public static void main(String[] args) {
//        String expression = "3+2*6-2";
        String expression = "5-2*3+1";
//        String expression = "7-6-1-8";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 charStack = new ArrayStack2(10);
        char[] chars = expression.toCharArray();
        int num1 = 0;
        int num2 = 0;
        int index = 0;//用于扫描
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch中

        String keys = "";//处理多位数

        //开始while循环扫描expression
        while (true) {
            //依次得到expression的每个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是否为运算符
            if(charStack.isOperation(ch)){//如果是运算符
                //当符号栈为空时,直接入栈
                if (charStack.isEmpty()) {
                    charStack.push(ch);
                } else {
                    //不为空时 比较优先级
                    int newOperation = charStack.priority(ch);
                    int inStackOperation = charStack.priority(charStack.showTop());
                    //新加入的运算符优先级小于或等于栈中运算符的优先级,就从数栈中pop出两个数,从符号栈中pop出一个运算符,进行运算,将得到的结果
                    //加入到数栈中去,将当前的操作符入符号栈
                    if (newOperation <= inStackOperation) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = charStack.pop();
                        res = numStack.calculate(num1, num2, oper);
                        numStack.push(res);
                        charStack.push(ch);
                    } else {
                        //新加入的运算符优先级大于栈中运算符的优先级,直接加入符号栈
                        charStack.push(ch);
                    }
                }
            }else{
                //处理多位数
                keys +=ch;

                if(index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keys));
                    break;
                }
                //如果当前字符的后一位是运算符的话直接将拼接的字符串转换成数字入栈
                if(charStack.isOperation(expression.substring(index+1,index+2).charAt(0))){
                    numStack.push(Integer.parseInt(keys));
                    //重置
                    keys = "";
                }
            }
            index++;
            if(index >= expression.length()){
                break;
            }
        }
        //当表达式扫描完毕后,就顺序的从数栈和符号栈中pop出相应的数字和符号,并运行。
        while (true) {
            //如果符号栈为空,则数栈剩余的最后一个数就是结果
            if (charStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = charStack.pop();
            res = numStack.calculate(num1, num2, oper);
            numStack.push(res);
        }
        System.out.println("表达式的" + expression + "=" + numStack.showTop());
    }
}

class ArrayStack2 {
    private int maxSize;//栈的大小
    private int top = -1;//栈顶
    private int[] stack;//栈(数组模拟栈)

    public ArrayStack2(int maxSize) {
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
    public void push(int value) {
        //判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        stack[++top] = value;
    }

    //出栈
    public int pop() {
        //判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈空...");
        }
        int value = stack[top--];
        return value;
    }

    //展示栈中的数据(从栈顶开始显示)
    public void list() {
        //判断栈是否为空
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println("stack[" + i + "]=" + stack[i]);
        }
    }

    //计算
    public int calculate(int num1, int num2, int operation) {
        int res = 0;
        switch (operation) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                System.out.println("运算符无法识别");
                break;
        }
        return res;
    }

    //判断运算符的优先级
    public int priority(int operation) {
        if (operation == '*' || operation == '/') {
            return 1;
        } else if (operation == '+' || operation == '-') {
            return 0;
        }
        return -1;
    }

    //判断是否是运算符
    public boolean isOperation(int operation) {
        return operation == '+' || operation == '-' || operation == '*' || operation == '/';
    }

    //显示栈顶数据
    public int showTop() {
        return stack[top];
    }
}
