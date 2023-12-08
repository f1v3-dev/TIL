package com.nhnacademy.test;

public class Fibonacci implements InfiniteIterator<Integer> {
    // TODO: 채우기

    int count = 0;

    int result = 0;
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        if (count == 0) {
            return 0;
        }

        if (count == 1) {
            return 1;
        }

        return count;
    }

}
