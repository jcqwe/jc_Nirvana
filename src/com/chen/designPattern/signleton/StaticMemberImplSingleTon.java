package com.chen.designPattern.signleton;


/**
 * 实现单例模式1（通过构造器私有化，静态常量成员属性实现）
 *
 * @author Administrator
 */
public class StaticMemberImplSingleTon {
    public static void main(String[] args) {

    }
}

class Person {

    private Person() {

    }

    private final static Person per = new Person();


    public Person getInstance() {
        return per;
    }
}
