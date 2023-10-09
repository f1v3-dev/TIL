package com.nhnacademy.simulation;

import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class HerbSimulationStream {
    static Random random = new Random();
    static int[] subsEfficacy = {90, 80, 70};

    public static double simulate(int trials) {

        return IntStream.range(0, trials)
                .mapToDouble(x -> {
                    if (random.nextBoolean()) {
                        System.out.println("약초를 구함!");
                        return 100.0;
                    } else {
                        int efficacy = subsEfficacy[random.nextInt(subsEfficacy.length)];
                        System.out.println("약초를 구하지 못함.. 효능 : " + efficacy);
                        return efficacy;
                    }
                })
                .average()
                .orElse(0);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("반복할 횟수를 입력하세요: ");
        int trials = sc.nextInt();

        float avgEfficacy = (float) simulate(trials);
        System.out.println("총 " + trials + " 횟수를 돌린 결과 평균 효능은 " + avgEfficacy + " 입니다.");
    }
}
