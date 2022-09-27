package com.chen.java8;

@FunctionalInterface
public interface MyInterface<T> {
    void test(T t);


    default void test01(T t){
        System.out.println(t);
    }

}
