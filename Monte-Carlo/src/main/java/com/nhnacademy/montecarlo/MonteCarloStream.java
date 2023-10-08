package com.nhnacademy.montecarlo;

import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class MonteCarloStream {

    static Random random = new Random();

    public static double estimatedPi(int totalCycle) {

        Stream<Double> stream = Stream.generate(() -> {
            double x = random.nextDouble();
            double y = random.nextDouble();

            double result = Math.pow(x, 2) + Math.pow(y, 2);
            if (result <= 1) {
                return 1.0;
            } else {
                return 0.0;
            }
        });

        return stream.limit(totalCycle)
                .mapToDouble(Double::doubleValue)
                .average()
                .getAsDouble() * 4;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("시도 횟수: ");
        int totalCycle = sc.nextInt();

        estimatedPi(totalCycle);
    }
}
