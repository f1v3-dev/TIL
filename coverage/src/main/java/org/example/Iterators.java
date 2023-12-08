package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * 이 클래스는 UndefinedIterator 인터페이스를 사용하는 Iterator를 위한 유틸리티 클래스입니다.
 * 따라서 이 클래스에 존재하는 메서드를 사용하기 위해서는 java.util.Iterator가 아닌
 * com.tip.functional.UndefinedIterator를 사용해야 합니다.
 *
 * [주의]
 * UndefinedIterator를 return 하는 메서드는 FiniteIterator와 InfiniteIterator를 return 하도록 설정하였으나,
 * 다른 메서드들에 사용하기 위해서는 toFiniteIterator, toInfiniteIterator 메서드를 통해
 * FiniteIterator와 InfiniteIterator로 다운 캐스팅을 해주어야 합니다.
 * 또는, 타입 캐스팅을 통해 사용할 수도 있습니다. (ex. (FiniteIterator) iterator)
 */

public class Iterators {

    /**
     * Check checkNotNull method.
     * @param element object
     * @param <T> element type
     * @throws IllegalArgumentException if element is null.
     * ----------------------------------------------------<br/>
     * 원래는 NullPointerException으로 예외처리를 하였으나,
     * 넘겨받는 매개변수의 Precondition을 체킹하는 것이기 때문에
     * 잘못된 매개변수를 넘겼다는 예외처리가 더 알맞은 것 같아 IllegalArgumentException으로 예외처리를 하였습니다.
     */
    public static <T> void checkParameterNull(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Iterators: element is Null");
        }
    }

    /**
     * Check checkNotInfinite method.
     * @param iterator UndefinedIterator
     * @param <T> type
     * @throws IllegalArgumentException if iterator instanceof InfiniteIterator.
     */
    public static <T> void checkNotInfinite(UndefinedIterator<T> iterator) {
        if (iterator instanceof InfiniteIterator) {
            throw new IllegalArgumentException("Iterators: iterator is infinite");
        }
    }

    /**
     * Check checkNotFinite method.
     * @param iterator UndefinedIterator
     * @param <T> type
     * @throws IllegalArgumentException if iterator instanceof FiniteIterator.
     */
    public static <T> void checkNotFinite(UndefinedIterator<T> iterator) {
        if (iterator instanceof FiniteIterator) {
            throw new IllegalArgumentException("Iterators: iterator is finite");
        }
    }

    /**
     * Iterators toFiniteIterator method.
     * [UndefinedIterator -> FiniteIterator로 다운 캐스팅하는 메서드입니다]
     *
     * [주의]
     * 사용자가 UndefinedIterator를 new를 통해 생성하여 hasNext()를 return true로 설정한 경우,
     * toFiniteIterator를 통해 다운 캐스팅을 진행한다 해도
     * InfiniteIterator와 같은 동작을 하는 치명적인 문제가 발생할 수 있습니다.
     *
     * @param UndefinedIterator UndefinedIterator
     * @return FiniteIterator
     * @param <T> type
     * @throws IllegalArgumentException if iterator is instanceof InfiniteIterator or iterator is null.
     */

    public static <T> FiniteIterator<T> toFiniteIterator(UndefinedIterator<T> UndefinedIterator) {

        checkParameterNull(UndefinedIterator);
        checkNotInfinite(UndefinedIterator);

        return new FiniteIterator<T>() {
            @Override
            public boolean hasNext() {
                return UndefinedIterator.hasNext();
            }

            @Override
            public T next() {
                return UndefinedIterator.next();
            }
        };
    }

    /**
     * Iterators toInfiniteIterator method.
     * UndefinedIterator -> InfiniteIterator로 다운 캐스팅하는 메서드입니다.
     *
     * [주의]
     * 사용자가 UndefinedIterator를 new를 통해 생성하여 hasNext()를 제한한 경우
     * toInfiniteIterator를 통해 다운 캐스팅을 진행한다 해도
     * FiniteIterator와 같은 동작을 하는 치명적인 문제가 발생할 수 있습니다.
     *
     * @param UndefinedIterator UndefinedIterator
     * @return InfiniteIterator
     * @param <T> type
     * @throws IllegalArgumentException if iterator is instanceof FiniteIterator or iterator is null
     */
    public static <T> InfiniteIterator<T> toInfiniteIterator(UndefinedIterator<T> UndefinedIterator) {

        checkParameterNull(UndefinedIterator);
        checkNotFinite(UndefinedIterator);
        return new InfiniteIterator<T>() {
            @Override
            public T next() {
                return UndefinedIterator.next();
            }
        };
    }

    /**
     * Iterators reduce method.
     * 기존에 존재했던 Iterable의 reduce를 사용하게 된다면, Iterator와의 관계를 끊을 수 없기 때문에
     * reduce를 FiniteIterator로만 사용할 수 있도록 변경하였습니다.
     * @param es non-null FiniteIterator
     * @param biFunction non-null BiFunction
     * @param init non-null init value
     * @return R type
     * @param <E> FiniteIterator elements type
     * @param <R> init type and return type
     */
    public static <E, R> R reduce(FiniteIterator<E> es, BiFunction<R, E, R> biFunction, R init) {

        checkParameterNull(es);
        checkParameterNull(biFunction);
        checkParameterNull(init);

        R result = init;
        while (es.hasNext()) {
            result = biFunction.apply(result, es.next());
        }
        return result;
    }


    /**
     * Iterators equals Method.
     * If xs and ys reference the same iterator, it returns false.
     * @param xs non-null iterator and NewIterator
     * @param ys non-null iterator and NewIterator
     * @param <T> type
     * @return if xs and ys elements are same, return true. else return false.
     * @throws IllegalArgumentException     if iterator is null.
     * @throws IllegalArgumentException if iterator is instanceof InfiniteIterator.
     */
    public static <T> boolean equals(FiniteIterator<T> xs, FiniteIterator<T> ys) {
        // TODO: reduce를 써서 -> 안쓰고

        checkParameterNull(xs);
        checkParameterNull(ys);

        while (xs.hasNext() && ys.hasNext()) {
            if (!xs.next().equals(ys.next())) {
                return false;
            }
        }

        return (!xs.hasNext() && !ys.hasNext());
    }


    /**
     * Iterators toString method.
     * @param es non-null iterator and not InfiniteIterator
     * @param separator non-null String
     * @param <T> type
     * @return es elements to String
     * @throws IllegalArgumentException if iterator is instanceof InfiniteIterator.
     * @throws IllegalArgumentException if separator is Null.
     */
    public static <T> String toString(FiniteIterator<T> es, String separator) {
        // TODO: redude를 써서
        checkParameterNull(es);
        checkParameterNull(separator);

        return es.hasNext() ? reduce(es, (r, x) -> r.append(separator).append(x),
                new StringBuilder(es.next().toString())).toString() : "";
    }

    public static <E, R> UndefinedIterator<R> map(UndefinedIterator<E> es, Function<E, R> function) {

        checkParameterNull(es);
        checkParameterNull(function);

        if (es instanceof InfiniteIterator) {
            return new InfiniteIterator<>() {
                @Override
                public R next() {
                    return function.apply(es.next());
                }
            };
        }


        return new FiniteIterator<>() {
            public boolean hasNext() {
                return es.hasNext();
            }

            public R next() {
                return function.apply(es.next());
            }
        };
    }


    /**
     * Iterators filter method.
     * @param iterator non-null iterator
     * @param predicate non-null predicate
     * @return Iterator
     * @param <E> type
     * @throws IllegalArgumentException if iterator is null or predicate is null.
     */
    public static <E> UndefinedIterator<E> filter(UndefinedIterator<E> iterator, Predicate<E> predicate) {
        // TODO: Bug를 찾을 수 있는 test code를 IteratorTest.filterTest에 쓰고, Bug 고치기
        // findFirst를 한번만 쓸 수 있게

        checkParameterNull(iterator);
        checkParameterNull(predicate);

        if (iterator instanceof InfiniteIterator) {
            return new InfiniteIterator<E>() {
                @Override
                public E next() {
                    return findFirst(iterator, predicate);
                }
            };
        }

        return new FiniteIterator<E>() {
            private E current;
            private boolean check = true;

            public boolean hasNext() {
                if (check) {
                    try {
                        current = findFirst(iterator, predicate);
                    } catch (NoSuchElementException e) {
                        return false;
                    }
                    check = !check;
                }

                return true;
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("filter");
                }
                check = !check;
                return current;
            }
        };
    }


    // InfiniteIterator를 넣고 계속해서 돌아가는 경우는 사용자가 predicate을 잘못 입력한 경우일 수도 있기 때문에
    // 개발자의 입장에서 이걸 막아주는건 맞지 않다고 생각 -> limit()을 통해 제한을 거는 경우도 존재할 수 있기 때문

    // TODO : return null을 하는게 맞는가? -> filter에서 처리를 어떻게 해줘야하는가?
    public static <E> E findFirst(UndefinedIterator<E> iterator, Predicate<E> predicate) {

        checkParameterNull(iterator);
        checkParameterNull(predicate);

        while (iterator.hasNext()) {
            E first = iterator.next();
            if (predicate.test(first)) {
                return first;
            }
        }

        // return null; -> null을 반환하는 것보다는 NoSuchElementException을 던지는게 맞다고 생각
        // iterator를 모두 돌린 경우기이 때문에 NoSuchElementException을 던져주는게 맞다고 생각
        throw new NoSuchElementException();
    }

    public static <T> InfiniteIterator<T> iterate(T seed, UnaryOperator<T> f) {

        checkParameterNull(seed);
        checkParameterNull(f);

        return new InfiniteIterator<T>() {
            T current = seed;

            @Override
            public T next() {
                T old = current;
                current = f.apply(current);
                return old;
            }
        };
    }

    /**
     * Iterators limit method.
     * @param iterator UndefinedIterator (FiniteIterator or InfiniteIterator)
     * @param maxSize long type size
     * @return FiniteIterator
     * @param <T> type
     * @throws IllegalArgumentException if iterator is null
     * @throws IllegalArgumentException if maxSize is negative number.
     * @throws IndexOutOfBoundsException if maxSize exceeds the iterator size.
     *
     * count(iterator)를 통해서 iterator의 size를 구하고, maxSize와 비교한 후,
     * maxSize가 count(iterator)의 값보다 작은 경우에만 Return new를 해주는 방식이 가장 올바르지만,
     * Iterator의 특성상 (Count 메서드를 호출하여) 한 번 돌리면 다시 돌릴 방안이 없기 때문에
     * hasNext()를 통해서 maxSize를 넘어가는지 확인하고, 넘어가면 IndexOutOfBoundsException을 던지는 방식으로 구현하였습니다.
     */
    public static <T> FiniteIterator<T> limit(UndefinedIterator<T> iterator, long maxSize) {
        // TODO
        checkParameterNull(iterator);

        if (maxSize < 0) {
            throw new IllegalArgumentException("limit: maxSize is negative");
        }

        return new FiniteIterator<T>() {
            long count = 0;

            @Override
            public boolean hasNext() {
                if (count < maxSize && !iterator.hasNext()) {
                    throw new IndexOutOfBoundsException("limit: maxSize is exceeded");
                }
                return count < maxSize;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("limit");
                }
                count = Math.addExact(count, 1);
                return iterator.next();
            }
        };
    }

    /**
     * Iterators generate method.
     * @param supplier Supplier
     * @return InfiniteIterator
     * @param <T> type
     * @throws IllegalArgumentException if supplier is null
     */
    public static <T> InfiniteIterator<T> generate(Supplier<T> supplier) {
        // TODO:
        checkParameterNull(supplier);
        return new InfiniteIterator<T>() {
            @Override
            public T next() {
                return supplier.get();
            }
        };
    }

    // Covariant return type
    public static <X, Y, Z> UndefinedIterator<Z> zip(BiFunction<X, Y, Z> biFunction, UndefinedIterator<X> xIterator,
                                                     UndefinedIterator<Y> yIterator) {

        checkParameterNull(biFunction);
        checkParameterNull(xIterator);
        checkParameterNull(yIterator);


        // 둘다 InfiniteIterator -> return Infinite
        if (xIterator instanceof InfiniteIterator && yIterator instanceof InfiniteIterator) {
            return new InfiniteIterator<Z>() {
                @Override
                public Z next() {
                    return biFunction.apply(xIterator.next(), yIterator.next());
                }
            };
        }

        // (xIterator || yIterator) instanceof NewIterator -> 포함되기만 하면 return New
        return new FiniteIterator<Z>() {
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


    /**
     * Iterators count method.
     * @param iterator non-null FiniteIterator
     * @return Number of elements in the Iterator
     * @param <E> type
     * @throws NullPointerException if iterator is null
     * @throws IllegalArgumentException if iterator instanceof InfiniteIterator
     */
    public static <E> long count(FiniteIterator<E> iterator) {
        // TODO: reduce를 써서
        checkParameterNull(iterator);

        long count = 0;
        while (iterator.hasNext()) {
            count = Math.addExact(count, 1);
            iterator.next();
        }

        return count;
    }


    /**
     * Iterators get method.
     *
     * 찾으려는 index의 값보다 iterator의 size가 작다면? (iterator size < index)
     * limit에서 hasNext()를 통해서 IndexOutOfBoundsException 예외처리를 하는 방식으로 진행하였습니다.
     *
     * @param iterator UndefinedIterator
     * @param index long type index of Iterator
     * @return element
     * @param <T> iterator element type
     * @throws IllegalArgumentException if iterator is null
     */
    public static <T> T get(UndefinedIterator<T> iterator, long index) {

        checkParameterNull(iterator);
        if (index < 0) {
            throw new IndexOutOfBoundsException("index < " + index);
        }

        return getLast(limit(iterator, Math.addExact(index, 1)));
    }

    private static <T> T getLast(UndefinedIterator<T> iterator) {
        checkParameterNull(iterator);
        while (true) {
            T current = iterator.next();
            if (!iterator.hasNext()) {
                return current;
            }
        }
    }

    public static <T> List<T> toList(FiniteIterator<T> iterator) {
        checkParameterNull(iterator);

        List<T> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    public static <E> void print(FiniteIterator<E> iterator, String separator, java.io.PrintStream printStream) {
        checkParameterNull(iterator);
        checkParameterNull(separator);
        checkParameterNull(printStream);

        printStream.print(toString(iterator, separator));
    }

    public static <E> void print(FiniteIterator<E> iterator, String separator) {
        checkParameterNull(iterator);
        checkParameterNull(separator);

        print(iterator, separator, System.out);
    }

    public static <E> void println(FiniteIterator<E> iterator, String separator, java.io.PrintStream printStream) {

        checkParameterNull(iterator);
        checkParameterNull(separator);
        checkParameterNull(printStream);

        print(iterator, separator, printStream);
        printStream.println();
    }

    public static <E> void println(FiniteIterator<E> iterator, String separator) {
        checkParameterNull(iterator);
        checkParameterNull(separator);
        println(iterator, separator, System.out);
    }

    public static <E> void println(FiniteIterator<E> iterator) {
        checkParameterNull(iterator);
        println(iterator, ", ");
    }

    private Iterators() {
    }
}