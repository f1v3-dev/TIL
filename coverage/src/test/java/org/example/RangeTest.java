package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RangeTest {
    // TODO: 모든 기능을 고르게 테스트 할 수 있는 코드 적어보기
    // 그리고 테스트 코드는 순서같은거 상관 없이 모두 다 Test OK가 된다고 가정해야된다.

    @Test
    @DisplayName("Range() : classInvariant를 어긴 경우 테스트")
    void constructor_ClassInvariant_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> new Range(1, 1));
        assertThrows(IllegalArgumentException.class, () -> new Range(10, 1));
        assertThrows(IllegalArgumentException.class, () -> new Range(-1));
        assertThrows(IllegalArgumentException.class, () -> new Range(Long.MIN_VALUE, Long.MAX_VALUE + 1));
        assertThrows(IllegalArgumentException.class, () -> new Range(Long.MIN_VALUE - 1, Long.MAX_VALUE));
    }

    @Test
    @DisplayName("Range() : 정상적으로 Range 객체가 생성되었는지 테스트")
    void constructor_NormalCase_ReturnRangeObject() {
        int start = 1;
        int end = 10;

        Range range = new Range(start, end);
        assertEquals(start, range.min());
        assertEquals(end, range.end());

        Range rangeWithoutStart = new Range(end);
        assertEquals(start, rangeWithoutStart.min());
        assertEquals(end, rangeWithoutStart.end());
    }

    @Test
    @DisplayName("closed() : classInvariant를 어긴 경우 테스트")
    void closed_ClassInvariant_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> Range.closed(1, Long.MAX_VALUE));
        assertThrows(IllegalArgumentException.class, () -> Range.closed(Long.MIN_VALUE - 1, Long.MAX_VALUE - 1));
    }

    @Test
    @DisplayName("closed() : 정상적으로 Range 객체가 생성되었는지 테스트")
    void closed_NormalCase_ReturnRangeObject() {

        int startInclusive = 1;
        int endExclusive = 9;

        Range closedRange = Range.closed(startInclusive, endExclusive);
        assertEquals(startInclusive, closedRange.min());
        assertEquals(endExclusive, closedRange.max());
    }

    @Test
    @DisplayName("max() : 정상적으로 max(endExclusive - 1)를 반환하는지 테스트")
    void maxTest() {
        int end = 10;
        Range range = new Range(end);
        assertEquals(end - 1, range.max());

        long endValue = Long.MAX_VALUE;
        Range range1 = new Range(endValue);
        assertEquals(endValue - 1, range1.max());
    }

    @Test
    @DisplayName("size() : 정상적으로 size를 반환하는지 테스트")
    void sizeTest() {
        BigInteger size = BigInteger.valueOf(10L);
        Range range = new Range(11);
        assertEquals(size, range.size());

        size = BigInteger.valueOf(Long.MAX_VALUE).subtract(BigInteger.valueOf(Long.MIN_VALUE));
        range = new Range(Long.MIN_VALUE, Long.MAX_VALUE);
        assertEquals(size, range.size());
    }

    @Test
    @DisplayName("iterator() : 정상적으로 Iterator를 반환하는지 테스트")
    void iteratorTest() {
        int startInclusive = 1;
        int endExclusive = 101;

        Range range = new Range(startInclusive, endExclusive);
        Iterator<Long> iterator = range.iterator();

        for (long i = startInclusive; i < endExclusive; i++) {
            assertEquals(i, iterator.next());
        }

        assertFalse(iterator::hasNext);
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}

