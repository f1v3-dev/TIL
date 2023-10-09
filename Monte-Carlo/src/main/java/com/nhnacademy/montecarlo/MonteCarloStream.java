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

            return Math.pow(x, 2) + Math.pow(y, 2);
        });

        long count = stream.limit(totalCycle)
                .filter(result -> result <= 1)
                .count();

        return 4 * ((double) count / totalCycle);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("시도 횟수: ");
        int totalCycle = sc.nextInt();

        System.out.println(estimatedPi(totalCycle));
    }
}
