package org.example;

import static org.example.Mathx.binaryDistribution;
import static org.example.Mathx.discreteUniformDistribution;
import static org.example.Mathx.fibonacci;
import static org.example.Mathx.gcd;
import static org.example.Mathx.normalDistribution;
import static org.example.Mathx.product;
import static org.example.Mathx.randDoubles;
import static org.example.Mathx.randEnumUniformlyDistributed;
import static org.example.Mathx.randInts;
import static org.example.Mathx.sum;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class MathxTest {

    /**
     * 아직 Mockito를 사용하는 방법을 모르기 때문에
     * Integration Testing으로 테스트하는 방법을 사용하였습니다.
     * random 값들을 테스트 하는 방법에 대해서 생각 필요..
     */

    private FiniteIterator<Integer> finiteIterator;
    private FiniteIterator<Double> doubleFiniteIterator;

    @BeforeEach
    void createIterators() {
        finiteIterator = new FiniteIterator<Integer>() {
            Integer count = 0;
            Integer current = 0;

            @Override
            public boolean hasNext() {
                return count < 5;
            }

            @Override
            public Integer next() {
                current = Math.addExact(current, 2);
                count = Math.addExact(count, 1);
                return current;
            }
        };

        doubleFiniteIterator = new FiniteIterator<Double>() {
            Integer count = 0;
            Double current = 0.0;

            @Override
            public boolean hasNext() {
                return count < 5;
            }

            @Override
            public Double next() {
                current = current + 1.5;
                count = Math.addExact(count, 1);
                return current;
            }
        };
    }

    @Test
    @DisplayName("checkParameterNull() : null element를 넘긴 경우 예외 처리 테스트")
    void checkParameterNull_NullElement_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> Mathx.checkParameterNull(null));
    }

    @Test
    @DisplayName("sum() : Parameter에 null을 넘긴 경우 테스트 예외 처리 테스트")
    void sum_NullParameter_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> sum((FiniteIterator<Number>) null));
        assertThrows(NullPointerException.class, () -> sum((Range) null));
    }


    @Test
    @DisplayName("sum() : 정상적인 FiniteIterator를 넘긴 경우 테스트 올바른 값이 나오는지 테스트")
    void sum_NormalCase_ReturnFiniteElementsSumValue() {

        int intResult = 2 + 4 + 6 + 8 + 10;
        assertEquals(intResult, sum(finiteIterator));

        double doubleResult = 1.5 + 3.0 + 4.5 + 6.0 + 7.5;
        assertEquals(doubleResult, sum(doubleFiniteIterator));
    }

    @Test
    @DisplayName("product() : null iterator를 넘긴 경우 테스트 예외 처리 테스트")
    void product_NullIterator_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> product(null));
    }

    @Test
    @DisplayName("product() : 정상적인 FiniteIterator를 넘긴 경우 테스트 올바른 값이 나오는지 테스트")
    void product_NormalCase_ReturnFiniteElementsProductValue() {
        int intResult = 2 * 4 * 6 * 8 * 10;
        assertEquals(intResult, product(finiteIterator));

        double doubleResult = 1.5 * 3.0 * 4.5 * 6.0 * 7.5;
        assertEquals(doubleResult, product(doubleFiniteIterator));
    }


    @Test
    @DisplayName("randDoubles() : 정상적으로 InfiniteIterator를 생성하는지 테스트")
    void randDoubles_NormalCase_ReturnInfiniteIterator() {
        assertTrue(Iterators.generate(Mathx::randDouble) instanceof InfiniteIterator);
        assertTrue(randDoubles() instanceof InfiniteIterator);

        Iterators.println(Iterators.limit(Iterators.generate(Mathx::randDouble), 20));
        Iterators.println(Iterators.limit(randDoubles(), 20));
    }

    @Test
    @DisplayName("randInts() : 정상적으로 InfiniteIterator를 생성하는지 테스트")
    void randInts_NormalCase_ReturnInfiniteIterator() {
        assertTrue(Iterators.generate(Mathx::randInt) instanceof InfiniteIterator);
        assertTrue(randInts() instanceof InfiniteIterator);

        Iterators.println(Iterators.limit(Iterators.generate(Mathx::randInt), 20));
        Iterators.println(Iterators.limit(randInts(), 20));
    }

    @Test
    @DisplayName("gcd() : 정상적으로 gcd를 구하는지 테스트")
    void gcd_NormalCase_ReturnCorrectValue() {
        assertEquals(1, gcd(3, 5));
        assertEquals(2, gcd(4, 6));
        assertEquals(3, gcd(9, 12));
        assertEquals(4, gcd(8, 12));
        assertEquals(5, gcd(5, 15));
    }

    @Test
    @DisplayName("randEnumUniformlyDistributed() : 정상적으로 enum의 size보다 작은 값을 반환하는지 테스트")
    void randEnumUniformlyDistributed_NormalCase_ReturnLowerThanEnumSize() {
        int qualityLength = Quality.values().length;

        int randInteger = randEnumUniformlyDistributed(Quality.class);

        assertTrue(randInteger >= 0 && randInteger <= qualityLength);
        assertInstanceOf(Integer.class, randInteger);
    }

    @Test
    @DisplayName("discreteUniformDistribution() : 잘못된 범위의 값을 넘긴 경우 예외 처리 테스트")
    void discreteUniformDistribution_IntegerOverflow_ExceptionThrown() {
        assertThrows(ArithmeticException.class,
                () -> discreteUniformDistribution(Integer.MIN_VALUE, Integer.MAX_VALUE));

        assertThrows(ArithmeticException.class, () -> discreteUniformDistribution(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("discreteUniformDistribution() : Parameter로 null을 넘긴 경우 예외 처리 테스트")
    void discreteUniformDistribution_NullParameter_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> discreteUniformDistribution(null));
    }

    @Test
    @DisplayName("discreteUniformDistribution() : 정상적으로 InfiniteIterator를 생성하는지 테스트")
    void discreteUniformDistributionTest_NormalCase_ReturnInfiniteIterator() {
        int origin = 1;
        int boundInclusive = 10;

        InfiniteIterator<Integer> discreteUniformDistributionWithOriginIterator =
                discreteUniformDistribution(origin, boundInclusive);
        assertInstanceOf(InfiniteIterator.class, discreteUniformDistributionWithOriginIterator);

        boundInclusive = 100;
        InfiniteIterator<Integer> discreteUniformDistributionZeroOriginIterator =
                discreteUniformDistribution(boundInclusive);
        assertInstanceOf(InfiniteIterator.class, discreteUniformDistributionZeroOriginIterator);

        InfiniteIterator<Integer> discreteUniformDistributionEnumIterator = discreteUniformDistribution(Quality.class);
        assertInstanceOf(InfiniteIterator.class, discreteUniformDistributionEnumIterator);
    }

    @Test
    @DisplayName("binaryDistribution() : 잘못된 확률(음수, 1보다 큰 값)을 넘긴 경우 예외 처리 테스트")
    void binaryDistribution_InvalidProbability_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> binaryDistribution(-0.5));
        assertThrows(IllegalArgumentException.class, () -> binaryDistribution(1.5));
    }

    @Test
    @DisplayName("binaryDistribution() : 정상적으로 InfiniteIterator를 생성하는지 테스트")
    void binaryDistribution_NormalCase_ReturnInfiniteIterator() {
        double probability = 0.5;
        InfiniteIterator<Integer> binaryDistributionIterator = binaryDistribution(probability);
        assertInstanceOf(InfiniteIterator.class, binaryDistributionIterator);
    }

    @Test
    @DisplayName("normalDistribution() : 정상적으로 InfiniteIterator를 생성하는지 테스트")
    void normalDistribution_NormalCase_ReturnInfiniteIterator() {

        double mean = 90;
        double standardDeviation = 10;
        InfiniteIterator<Double> normalDistributionIterator = normalDistribution(mean, standardDeviation);
        assertInstanceOf(InfiniteIterator.class, normalDistributionIterator);
    }

    @Test
    @DisplayName("fibonacci() : 정상적으로 InfiniteIterator(fibonacci)를 생성하는지 테스트")
    void fibonacci_NormalCase_ReturnInfiniteIterator() {
        InfiniteIterator<Integer> fibonacci = fibonacci();
        assertInstanceOf(Fibonacci.class, fibonacci);
        assertInstanceOf(InfiniteIterator.class, fibonacci);
    }
}