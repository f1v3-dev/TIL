package com.nhnacademy;

import java.util.Random;
import java.util.Scanner;

/**
 * "자바를 사용하여 Monte Carlo 방법을 이용해 원주율(π)을 추정하는 프로그램을 작성하세요. 이 프로그램은 다음 요구사항들을 충족해야 합니다
 * 1. MonteCarlo라는 클래스를 생성하세요. 이 클래스는 두 개의 private 인스턴스 변수, 즉 시뮬레이션의 반복 횟수(iterations)와 원 안에 들어간 점의 수(insideCircle)를 가지고 있어야 합니다.
 * 2. MonteCarlo 클래스에는 type parameter 'T'가 있어야 하며, 이 'T'는 Number 타입 또는 그 하위 타입만 가능하도록 해야 합니다.
 * 3. MonteCarlo 클래스에는 생성자와 estimatePi()라는 메소드가 있어야 합니다:
 * 4. 생성자에서는 반복 횟수를 입력받아 초기화하고, insideCircle 변수를 0으로 초기화합니다.
 * 5. estimatePi() 메소드에서 Monte Carlo 방법을 사용하여 π 값을 추정하고 결과를 반환합니다.
 * 6. 각 점의 x와 y 좌표 값은 [0,1] 범위에서 uniform distribution으로 무작위로 생성되어야 하며, 각 점이 단위원 내부에 있는지 판단하여 π 값을 추정합니다.
 * 최종적으로 main 함수에서 MonteCarlo<Integer> 객체를 만들고 π 값을 추정한 후 출력합니다."
 */
public class MonteCarlo<T extends Number> {

    private static final Random random = new Random();

    private final double iterations;

    private double insideCircle;

    public MonteCarlo(int iterations) {
        this.iterations = iterations;
        this.insideCircle = 0;
    }

    public double estimatePi() {

        // estimatePi() 메소드에서 Monte Carlo 방법을 사용하여 π 값을 추정하고 결과를 반환합니다.

        // x, y 좌표는 0 ~ 1 사이의 값 -> 소수점이면 된다.
        // x^2 + y^2 <= 1 사이의 값

        for (int i = 0; i < iterations; i++) {

            double result = uniformDistribution(random);
            if (result <= 1) {
                insideCircle++;
            }
        }

        return 4 * (insideCircle / iterations);
    }

    public int countCircles() {
        return (int) this.insideCircle;
    }

    public double uniformDistribution(Random random) {
        double x = random.nextDouble();
        double y = random.nextDouble();

        return Math.pow(x, 2) + Math.pow(y, 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("반복할 횟수를 입력하세요 : ");
        int iterations = sc.nextInt();

        MonteCarlo<Integer> monteCarlo = new MonteCarlo<>(iterations);

        double estimatedPi = monteCarlo.estimatePi();
        int insideCircles = monteCarlo.countCircles();


        System.out.println("Inside Circle Counts: " + insideCircles);
        System.out.printf("Estimated Pi: %.5f\n", estimatedPi);

    }
}
