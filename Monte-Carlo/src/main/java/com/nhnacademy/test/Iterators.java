package com.nhnacademy.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Iterators {

    public static <E, R> R reduce(Iterable<E> es, BiFunction<R, E, R> biFunction, R init) {
        R result = init;
        for (E e : es) {
            result = biFunction.apply(result, e);
        }
        return result;
    }

    public static <E, R> R reduce(Iterator<E> es, BiFunction<R, E, R> biFunction, R init) {
        return reduce(() -> es, biFunction, init);
    }

    public static <T> boolean equals(Iterator<T> xs, Iterator<T> ys) {

        // TODO: reduce, zip을 써서
        Iterator<Boolean> zip;
        try {
            zip = zip(new BiFunction<T, T, Boolean>() {
                @Override
                public Boolean apply(T x, T y) {

                    boolean equals = x.equals(y);
                    if (!equals) {
                        return false;
                    }
                    return true;
                }
            }, xs, ys);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return reduce(zip, new BiFunction<Boolean, Boolean, Boolean>() {
            @Override
            public Boolean apply(Boolean bool1, Boolean bool2) {

                return bool1.equals(bool2);
            }
        }, true);
    }

    public static <T> String toString(Iterator<T> es, String separator) {
        // TODO: reduce를 써서
        return reduce(es, (s, t) -> {
            if (s.isEmpty()) {
                return t.toString();
            }
            return s + separator + t;
        }, "");

    }

    public static <E, R> Iterator<R> map(Iterator<E> es, Function<E, R> function) {
        return new Iterator<R>() {
            public boolean hasNext() {
                return es.hasNext();
            }

            public R next() {
                return function.apply(es.next());
            }
        };
    }

    public static <E> Iterator<E> filter(Iterator<E> iterator, Predicate<E> predicate) {
        // TODO: Bug를 찾을 수 있는 test code를 IteratorTest.filterTest에 쓰고, Bug 고치기
        // findFirst를 써서 풀기
        return new Iterator<E>() {
            private E current;

            public boolean hasNext() {
                while (iterator.hasNext()) {
                    current = iterator.next();
                    if (predicate.test(current)) {
                        return true;
                    }
                }
                return false;
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("filter");
                }
                return current;
            }
        };
    }

    public static <E> E findFirst(Iterator<E> iterator, Predicate<E> predicate) {
        while (iterator.hasNext()) {
            E first = iterator.next();
            if (predicate.test(first)) {
                return first;
            }
        }
        return null;
    }

    public static <T> InfiniteIterator<T> iterate(T seed, UnaryOperator<T> f) {
        return new InfiniteIterator<T>() {
            T current = seed;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                T old = current;
                current = f.apply(current);
                return old;
            }

        };
    }

    public static <T> Iterator<T> limit(Iterator<T> iterator, long maxSize) {
        // TODO
        return new Iterator<T>() {
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < maxSize;
            }

            @Override
            public T next() {
                count++;
                return iterator.next();
            }
        };
    }

    public static <T> InfiniteIterator<T> generate(Supplier<T> supplier) {
        // TODO:
        supplier = (Supplier<T>) new Supplier<Double>() {
            @Override
            public Double get() {
                return  ThreadLocalRandom.current().nextDouble();
            }
        };


        T t = supplier.get();
        return limit(iterate(t, x -> x + 1), 1);
    }

    public static <X, Y, Z> Iterator<Z> zip(BiFunction<X, Y, Z> biFunction, Iterator<X> xIterator,
                                            Iterator<Y> yIterator) {
        return new Iterator<Z>() {
            public boolean hasNext() {
                return xIterator.hasNext() && yIterator.hasNext();
            }

            public Z next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("zip");
                }
                return biFunction.apply(xIterator.next(), yIterator.next());
            }
        };
    }

    public static <E> long count(Iterator<E> iterator) {
        // TODO: reduce를 써서
    }

    public static <T> T get(Iterator<T> iterator, long index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index < " + index);
        }
        return getLast(limit(iterator, index + 1));
    }

    private static <T> T getLast(Iterator<T> iterator) {
        while (true) {
            T current = iterator.next();
            if (!iterator.hasNext()) {
                return current;
            }
        }
    }

    public static <T> List<T> toList(Iterator<T> iterator) {
        List<T> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    public static <E> void print(Iterator<E> iterator, String separator, java.io.PrintStream printStream) {
        printStream.print(toString(iterator, separator));
    }

    public static <E> void print(Iterator<E> iterator, String separator) {
        print(iterator, separator, System.out);
    }

    public static <E> void println(Iterator<E> iterator, String separator, java.io.PrintStream printStream) {
        print(iterator, separator, printStream);
        printStream.println();
    }

    public static <E> void println(Iterator<E> iterator, String separator) {
        println(iterator, separator, System.out);
    }

    public static <E> void println(Iterator<E> iterator) {
        println(iterator, ", ");
    }

    private Iterators() {
    }

    public static void main(String[] args) {

        boolean equals = Iterators.equals(
                limit(iterate(1, x -> x + 2), 10),
                Stream.iterate(1, x -> x + 1).limit(5).iterator());

        System.out.println(equals);
    }
}



