package com.nhnacademy.guava;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public class Test02 {
    public static void main(String[] args) {
        List<Integer> number = Lists.newArrayList(1, 8, 4, 10, 5, 9, 2, 3, 6, 7);

        Ordering<Integer> descNumber = new Ordering<Integer>() {
            @Override
            public int compare(@Nullable Integer origin, @Nullable Integer target) {
                return Ints.compare(origin, target);
            }
        };

        Collections.sort(number, descNumber);
        System.out.println(number);
    }
}
