package com.nhnacademy.montecarlo;

import java.util.Scanner;

public class MonteCarloTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("반복할 횟수를 입력하세요: ");

        int iteration = sc.nextInt();
        MonteCarlo<Integer> monteCarlo = new MonteCarlo<>(iteration);

        int insideCircleCount = 0;

        while (monteCarlo.hasNext()) {
            if (monteCarlo.next()) {
                insideCircleCount++;
            }
        }

        // 4 * (원 안에 존재하는 경우 카운트 / 총 반복 횟수)
        double estimatedPi = 4 * ((double) insideCircleCount / iteration);

        System.out.println("Inside Circle count: " + insideCircleCount);
        System.out.printf("Estimated Pi: %.5f\n", estimatedPi);
    }
}
