package com.example.demo.service.impl;

import java.util.*;

/**
 * lambda表达式 遍历map和list
 * Created by Administrator on 2018/7/19.
 */
public class TestService {
    public static void main (String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);
        map.put("E", 5);
        map.put("F", 6);
        map.forEach((k, v) -> {
            System.out.println("key: " + k + ", value: " + v);
            if(Objects.equals(k, "F")){
                System.out.println("hello " + v);
            }
        });

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        list.forEach(s -> {
            if(Objects.equals(s, "C")) {
                System.out.println(s);
            }
        });
    }
}
