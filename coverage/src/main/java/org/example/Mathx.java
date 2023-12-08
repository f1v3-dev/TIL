package org.example;

import static org.example.Iterators.*;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class Mathx {
    private Mathx() {
    }

    /**
     * Check element is null
     * @param element element to check
     * @param <T> type of element
     * @throws IllegalArgumentException if element is null.
     *
     * ----------------------------------------------------
     * Iterators에 있는 checkParameterNull()과 중복되는 코드
     * 원래, Precondition이라는 클래스를 만들어 checkParameterNull()이라는 메서드를 만들려고 하였으나,
     * 테스트를 진행할 때 Integration testing이 되어버려(Mockito를 사용하는 방법을 아직 모르는 탓에)
     * 각 클래스에 checkParameterNull()을 만들어서 사용하였습니다.
     */
    public static <T> void checkParameterNull(T element) {
        if (element == null) {
            throw new IllegalArgumentException("element is null");
        }
    }


    public static int randInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static int randInt(int origin, int boundExclusive) {
        return ThreadLocalRandom.current().nextInt(origin, boundExclusive);
    }

    public static double randDouble() {
        return ThreadLocalRandom.current().nextDouble();
    }

    public static InfiniteIterator<Double> randDoubles() {
        return generate(Mathx::randDouble);
    }

    public static InfiniteIterator<Integer> randInts() {
        return generate(Mathx::randInt);
    }

    public static <T extends Number> double sum(FiniteIterator<T> numbers) {
        checkParameterNull(numbers);
        return reduce(numbers, (x, y) -> x.doubleValue() + y.doubleValue(), 0D);
    }


    public static BigInteger sum(Range range) {
        final long max = range.max();
        final long min = range.min();

        return range.size().multiply(BigInteger.valueOf(Math.addExact(max, min))).divide(BigInteger.TWO);
    }

    public static <T extends Number> double product(FiniteIterator<T> numbers) {

        checkParameterNull(numbers);
        return reduce(numbers, (x, y) -> x.doubleValue() * y.doubleValue(), 1D);
    }

//    public static long product(Range range) {
//        return reduce(range, Math::multiplyExact, 1L);
//    }

    public static long gcd(long x, long y) {
        return BigInteger.valueOf(x).gcd(BigInteger.valueOf(y)).longValueExact();
    }

    public static boolean dirichletTest() {
        return gcd(randInt(), randInt()) == 1;
    }

    /**
     * discrete uniform distribution (이산 균등 분포)
     * @param origin min value
     * @param boundInclusive max value
     * @return InfiniteIterator
     * @throws ArithmeticException if boundInclusive + 1 > Integer.MAX_VALUE
     */
    public static InfiniteIterator<Integer> discreteUniformDistribution(int origin, int boundInclusive) {
        int bound = Math.addExact(boundInclusive, 1);
        return generate(() -> randInt(origin, bound));
    }

    /**
     * discrete uniform distribution (이산 균등 분포)
     * @param boundInclusive max value
     * @return InfiniteIterator
     * @throws ArithmeticException if boundInclusive + 1 > Integer.MAX_VALUE
     */
    public static InfiniteIterator<Integer> discreteUniformDistribution(int boundInclusive) {
        int bound = Math.addExact(boundInclusive, 1);
        return generate(() -> randInt(0, bound));
    }

    public static <T> int randEnumUniformlyDistributed(Class<T> enumType) {
        checkParameterNull(enumType);
        T[] constants = enumType.getEnumConstants();
        return randInt(0, constants.length);
    }

    public static <T extends Enum<T>> InfiniteIterator<Integer> discreteUniformDistribution(Class<T> enumType) {
        checkParameterNull(enumType);
        return generate(() -> randEnumUniformlyDistributed(enumType));
    }

    public static double randDoubleNormallyDistributed(double mean, double standardDeviation) {
        return ThreadLocalRandom.current().nextGaussian() * standardDeviation + mean;
    }

    public static InfiniteIterator<Double> normalDistribution(double mean, double standardDeviation) {
        return generate(() -> randDoubleNormallyDistributed(mean, standardDeviation));
    }

    // Bernoulli distribition
    public static InfiniteIterator<Integer> binaryDistribution(double probability) {
        if (probability < 0 || probability > 1) {
            throw new IllegalArgumentException("Out of range with " + probability);
        }
        return generate(() -> randDouble() <= probability ? 1 : 0);
    }

    public static InfiniteIterator<Integer> fibonacci() {

        return new Fibonacci();
    }
}
