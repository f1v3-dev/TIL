package org.example;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class Range implements Iterable<Long> {
    private long startInclusive;
    private long endExclusive;

    /**
     * Range 생성자
     * classInvariant를 지키지 않는 경우 IllegalArgumentException 발생시켜
     * 객체 자체를 생성하지 못하도록 변경
     */
    public Range(long startInclusive, long endExclusive) {
        classInvariant(startInclusive, endExclusive);
        this.startInclusive = startInclusive;
        this.endExclusive = endExclusive;
    }

    public Range(long endExclusive) {
        this(1, endExclusive);
    }

    public static Range closed(long startInclusive, long endInclusive) {
        try {
            endInclusive = Math.addExact(endInclusive, 1);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Range: value of end is exceeded the limit of long type!!");
        }

        return new Range(startInclusive, endInclusive);
    }

    /**
     * Range classInvariant method.
     * @param min : startInclusive
     * @param max : endExclusive
     * -------------
     * 기존의 classInvariant() 메서드에서는
     * if문에서 검증되지 않은 max()를 호출하여
     * max() 메서드에서 ArithmeticException 예외를 발생할 수도 있는 문제가 존재하여
     * 이를 해결하기 위해 새로운 classInvariant() 메서드를 만들었습니다.
     */
    private void classInvariant(long min, long max) {
        try {
            max = Math.subtractExact(max, 1);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Range: precondition failed.");
        }

        if (max < min) {
            throw new IllegalArgumentException("Range: precondition failed. (" + min + " > " + max + ")");
        }
    }

    private void classInvariant() {
        // max()를 통해서 classInvariant 확인을 한다?
        if (max() < min()) {
            throw new IllegalArgumentException("Range: " + this.min() + " > " + this.max());
        }
    }

    public long max() {
        return Math.subtractExact(endExclusive, 1);
    }

    public long min() {
        return this.startInclusive;
    }

    public long end() {
        return this.endExclusive;
    }

    public BigInteger size() {
        // 사이즈 long -> long.MinValue ~ log.MaxValue는 long 타입으로 표현할 수 없는 값이 나와버린다.
        // 따라서 BigInteger 타입으로 변경
//        return Math.subtractExact(this.end(), this.min());
        return BigInteger.valueOf(this.end()).subtract(BigInteger.valueOf(this.min()));
    }

    public Iterator<Long> iterator() {
        return new Iterator<>() {

            private long current = min();

            public boolean hasNext() {
                return current < end();
            }

            public Long next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Range.iterator()");
                }
                long value = current;
                current = Math.addExact(current, 1);
                return value;
            }
        };
    }
}