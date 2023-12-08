package com.nhnacademy.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RangeTest {
    @Test
    public void rangeTest() {
        // TODO: 모든 기능을 고르게 테스트 할 수 있는 코드 적어보기
        constructorTest();
        violateConstructorTest();
        iteratorTest();
        closedTest();
    }

    @Test
    @DisplayName("Constructor Test")
    void constructorTest() {
        long startInclusive = 1;
        long endExclusive = 100;
        Range firstRange = new Range(startInclusive, endExclusive);

        Range secondRange = new Range(endExclusive);

        assertEquals(firstRange.min(), secondRange.min());
        assertEquals(firstRange.max(), secondRange.max());
        assertEquals(firstRange.end(), secondRange.end());
        assertEquals(firstRange.size(), secondRange.size());
    }

    @Test
    @DisplayName("violate Constructor Test")
    void violateConstructorTest() {
        assertThrows(IllegalArgumentException.class, () -> new Range(5, 1));
    }

    @Test
    @DisplayName("Iterator Test")
    void iteratorTest() {
        Iterator<Long> iterator = new Range(11).iterator();

        long result = 0;
        while (iterator.hasNext()) {
            result += iterator.next();
        }
        assertEquals(55, result);
    }

    @Test
    @DisplayName("Closed Test")
    void closedTest() {
        Iterator<Long> closedIterator = Range.closed(1, 10).iterator();
        Iterator<Long> iterator = new Range(1, 11).iterator();

        assertTrue(Iterators.equals(closedIterator, iterator));
    }
}