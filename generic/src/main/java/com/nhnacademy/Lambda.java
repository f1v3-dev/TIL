package com.nhnacademy;

@FunctionalInterface
interface Calc {
    public int min(int x, int y);
}

public class Lambda {
    public static void main(String[] args) {
        Calc calc = (x, y) -> x < y ? x : y;

        System.out.println(calc.min(3, 5));
    }

}
