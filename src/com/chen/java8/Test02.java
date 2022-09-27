package com.chen.java8;

import org.junit.Test;

public class Test02 {

    @Test
    public void test() {
        MyInterface<Integer> m1 = s -> System.out.println(s);

        m1.test(1);
        m1.test01(2);
    }
}
