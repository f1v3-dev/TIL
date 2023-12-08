package com.nhnacademy.guava;

import com.google.common.base.Splitter;
import java.util.Iterator;

public class Test01 {
    public static void main(String[] args) {
        String fruits = "사과딸기레몬포도";

        Iterable<String> split = Splitter.fixedLength(2).split(fruits);

        Iterator<String> iterator = split.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
