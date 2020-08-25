package com.example.springbootdemotest.streamTest;


import java.util.*;
import java.util.stream.Stream;


public class StreamTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Map<String,Double> map = new HashMap<>();
        map.put("12",12.0);
//        map.
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        Stream<List<String>> stream = Stream.of(list);
        Stream<Map<String,Double>> streamMap = Stream.of(map);
        stream.forEach(x->{
            System.out.println(x);
        });
        streamMap.forEach(a ->{
            System.out.println(a);
        });
    }
}
