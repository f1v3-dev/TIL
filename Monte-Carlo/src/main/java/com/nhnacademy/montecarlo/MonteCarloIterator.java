package com.nhnacademy.montecarlo;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class MonteCarloIterator {

    static int innerPointCount = 0;
    static Random random = new Random();


    public static double estimatePi(int totalCycle) {

        Iterator<Double> iterator = new Iterator<Double>() {
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < totalCycle;
            }

            @Override
            public Double next() {
                count++;

                double x = random.nextDouble();
                double y = random.nextDouble();
                double result = Math.pow(x, 2) + Math.pow(y, 2);

                // 4 * (innerPointCount / totalCycle);
                if (result <= 1) {
                    innerPointCount++;
                }
                return (double) innerPointCount / count * 4;
            }
        };


        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        return (double) innerPointCount / totalCycle * 4;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("시도 횟수를 입력하세요: ");
        int totalCycle = sc.nextInt();

        estimatePi(totalCycle);
    }
}
