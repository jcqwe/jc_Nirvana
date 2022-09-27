package com.chen.java8;

import org.junit.Test;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Description java8新特性
 * Lambda表达式
 * (1)  -> 左边是形参列表
 * (2)  -> 右边是方法体
 * (3)  -> Lambda操作符
 * @Author chc
 * @CreateDate 2022/6/23 9:13
 * @Version 1.0
 **/
public class Test01 {

    @Test
    public void test01() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int res = comparator.compare(1, 2);
        System.out.println(res);

        System.out.println("xxxxxxxxxxxxxxxxxx");

        //Lambda表达式写法
        Comparator<Integer> com1 = Comparator.comparingInt(o -> o);
        int res1 = com1.compare(1, 2);
        System.out.println(res1);

        System.out.println("xxxxxxxxxxxxxxxxxx");

        //方法引用
        Comparator<Integer> com2 = Integer :: compare;
        int res2 = com2.compare(1, 2);
        System.out.println(res2);
    }
    //Lambda表达式左边没有形参 右边没有返回值
    @Test
    public void test02(){
        Runnable r1 = () -> System.out.println("123");
        r1.run();
    }
    //Lambda表达式左边有形参 右边没有返回值
    @Test
    public void test03(){
        Consumer<String> c1 = (s) -> System.out.println(s);
        c1.accept("123");
    }
    @Test
    public void test04(){
        List<Integer> list = Arrays.asList(1,0,3,1,20,10,9);
        list.stream().filter(item -> item > 10).forEach(System.out::println);
    }
    @Test
    public void test05(){
        BigDecimal totalNum = new BigDecimal(Double.toString(1.1));
        BigDecimal actualNum = new BigDecimal(Double.toString(1.0));

        double v = totalNum.subtract(actualNum).doubleValue();
        System.out.println(v);
    }

}
