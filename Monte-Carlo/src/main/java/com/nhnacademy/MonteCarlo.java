package com.nhnacademy;

import java.util.Random;

public class MonteCarlo {

    public static void main(String[] args) {

        int count = 0;

        Random random = new Random();

        for (int i = 0; i < 30_000; i++) {

            double x = random.nextDouble();
            double y = random.nextDouble();

            double result = Math.pow(x, 2) + Math.pow(y, 2);
            if (result <= 1) {
                count++;
            }

            System.out.println("x : " + x + ", y: " + y + " = " + result + " | count : " + count);
        }
    }
}
