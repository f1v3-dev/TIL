package com.nhnacademy.simulation;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class HerbSimulationIterator {
    static Random random = new Random();
    static int[] subsEfficacy = {90, 80, 70};

    public static double simulate(int trials) {
        int totalEfficacy = 0;
        Iterator<Integer> iterator = IntStream.range(0, trials).iterator();

        while (iterator.hasNext()) {
            iterator.next();
            if (random.nextBoolean()) {
                totalEfficacy += 100;
            } else {
                totalEfficacy += subsEfficacy[random.nextInt(subsEfficacy.length)];
            }
        }

        return (double) totalEfficacy / trials;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("반복할 횟수를 입력하세요: ");
        int trials = sc.nextInt();

        double avgEfficacy = simulate(trials);
        System.out.println("After " + trials + " trials, the average efficacy : " + avgEfficacy);

    }
}
