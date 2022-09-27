package com.chen.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
        Target t1 = new Target("123", "你在");
        Target t2 = new Target("123", "狗交");
        Target t3 = new Target("123", "什么");

        List<Target> lists = new ArrayList<>();
        lists.add(t1);
        lists.add(t2);
        lists.add(t3);

        lists = lists.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Target::getPlateNo))), ArrayList::new));
        for (Target list : lists) {
            System.out.println(list);
        }
        System.out.println(lists.size());
    }
}

class Target implements Serializable {
    private static final long serialVersionUID = -8124256712776938834L;
    private String plateNo;
    private String name;

    public Target(String plateNo, String name) {
        this.plateNo = plateNo;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    @Override
    public String toString() {
        return "Target{" +
                "plateNo='" + plateNo + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
