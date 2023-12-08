package org.example;


import static org.example.Iterators.checkNotFinite;
import static org.example.Iterators.checkNotInfinite;
import static org.example.Iterators.checkParameterNull;
import static org.example.Iterators.filter;
import static org.example.Iterators.iterate;
import static org.example.Iterators.limit;
import static org.example.Iterators.map;
import static org.example.Iterators.reduce;
import static org.example.Iterators.toFiniteIterator;
import static org.example.Iterators.toInfiniteIterator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IteratorsTest {

/*    @Test
    void iterateTest() {
        assertTrue(iterate(1, x -> x + 1) instanceof InfiniteIterator);




        assertTrue(limit(iterate(1, x -> x + 1), 10) instanceof FiniteIterator);
        Iterators.equals(limit(iterate(1, x -> x + 1), 10), Stream.iterate(1, x -> x + 1).limit(5).iterator());

        assertTrue(!Iterators.equals(limit(iterate(1, x -> x + 1), 10),
                Stream.iterate(1, x -> x + 1).limit(5).iterator()));
        assertTrue(Iterators.equals(limit(iterate(1, x -> x + 1), 10),
                Stream.iterate(1, x -> x + 1).limit(10).iterator()));
        assertEquals(Iterators.toString(limit(iterate(1, x -> x + 1), 10), " "),
                Stream.iterate(1, x -> x + 1).limit(10).map(String::valueOf).reduce((x, y) -> x + " " + y).orElse(""));
        assertEquals(Iterators.toString(limit(iterate(1, x -> x + 1), 10), ","),
                Stream.iterate(1, x -> x + 1).limit(10).map(String::valueOf).collect(Collectors.joining(",")));
    }*/

    private FiniteIterator<Integer> finiteIterator;
    private InfiniteIterator<Integer> infiniteIterator;

    @BeforeEach
    public void createIterator() {
        finiteIterator = new FiniteIterator<Integer>() {
            Integer count = 0;

            @Override
            public boolean hasNext() {
                return count < 10;
            }

            @Override
            public Integer next() {
                count = Math.addExact(count, 1);
                return count;
            }
        };

        infiniteIterator = new InfiniteIterator<Integer>() {
            Integer count = 0;

            @Override
            public Integer next() {
                count = Math.addExact(count, 1);
                return count;
            }
        };
    }

    @Test
    @DisplayName("checkParameterNull() : null element를 넘긴 경우 테스트")
    void checkParameterNull_NullElement_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> checkParameterNull(null));
    }

    @Test
    @DisplayName("checkNotInfinite() : InfiniteIterator를 넘겼을 때 정상적으로 예외를 던지는지 테스트")
    void checkNotInfinite_NotInfiniteIterator_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> checkNotInfinite(infiniteIterator));
    }

    @Test
    @DisplayName("checkNotFinite() : FiniteIterator를 넘겼을 때 정상적으로 예외를 던지는지 테스트")
    void checkNotFinite_NotFiniteIterator_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> checkNotFinite(finiteIterator));
    }

    @Test
    @DisplayName("toFiniteIterator() : Parameter가 null인 경우 예외 처리 테스트")
    void toFiniteIterator_NullParameter_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> toFiniteIterator(null));
    }

    @Test
    @DisplayName("toFiniteIterator() : InfiniteIterator를 넘긴 경우 예외 처리 테스트")
    void toFiniteIterator_InfiniteIterator_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> toFiniteIterator(infiniteIterator));
    }

    @Test
    @DisplayName("toFiniteIterator() : 정상적으로 UndefinedIterator에서 FiniteIterator로 변환되는지 테스트")
    void toFiniteIterator_NormalCase_Converted() {
        UndefinedIterator<Integer> UndefinedIterator = new UndefinedIterator<Integer>() {
            Integer current = 0;
            Integer count = 0;

            @Override
            public boolean hasNext() {
                return count < 3;
            }

            @Override
            public Integer next() {
                current = Math.addExact(current, 5);
                count = Math.addExact(count, 1);
                return current;
            }
        };

        FiniteIterator<Integer> definedFinite = Iterators.toFiniteIterator(UndefinedIterator);
        assertInstanceOf(FiniteIterator.class, definedFinite);
        assertEquals(5, definedFinite.next());
        assertEquals(10, definedFinite.next());
        assertEquals(15, definedFinite.next());
        assertFalse(definedFinite.hasNext());
    }

    @Test
    @DisplayName("toInfiniteIterator() : Parameter가 null인 경우 예외 처리 테스트")
    void toInfiniteIterator_NullParameter_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> toInfiniteIterator(null));
    }

    @Test
    @DisplayName("toInfiniteIterator() : FiniteIterator를 넘긴 경우 예외 처리 테스트")
    void toInfiniteIterator_FiniteIterator_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> toInfiniteIterator(finiteIterator));
    }

    @Test
    @DisplayName("toInfiniteIterator() : 정상적으로 UndefinedIterator에서 InfiniteIterator로 변환되는지 테스트")
    void toInfiniteIterator_NormalCase_Converted() {
        UndefinedIterator<Integer> UndefinedIterator = new UndefinedIterator<Integer>() {
            Integer current = 0;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                current = Math.addExact(current, 5);
                return current;
            }
        };

        InfiniteIterator<Integer> definedInfinite = Iterators.toInfiniteIterator(UndefinedIterator);
        assertInstanceOf(InfiniteIterator.class, definedInfinite);
        assertEquals(5, definedInfinite.next());
        assertEquals(10, definedInfinite.next());
        assertEquals(15, definedInfinite.next());
        assertEquals(20, definedInfinite.next());

        assertTrue(definedInfinite.hasNext());
    }

    @Test
    @DisplayName("reduce() : Parameter로 null을 넘겨준 경우 예외 처리 테스트")
    void reduce_NullIterator_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> reduce(null, (x, y) -> x, 0));
        assertThrows(IllegalArgumentException.class, () -> reduce(finiteIterator, null, 0));
        assertThrows(IllegalArgumentException.class, () -> reduce(finiteIterator, (x, y) -> x, null));
    }

    @Test
    @DisplayName("reduce() : FiniteIterator를 넘겨준 경우 정상적으로 작동하는지 테스트")
    void reduce_NormalCase_Converted() {
        BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> x + y;
        Integer reduce = reduce(finiteIterator, biFunction, 0);
        assertEquals(55, reduce);

        FiniteIterator<Integer> stringFinite = new FiniteIterator<Integer>() {
            Integer count = 0;

            @Override
            public boolean hasNext() {
                return count < 5;
            }

            @Override
            public Integer next() {
                count = Math.addExact(count, 1);

                return count;
            }
        };

        BiFunction<String, Integer, String> stringBiFunction = (x, y) -> x + y;
        String init = "";

        assertEquals("12345", reduce(stringFinite, stringBiFunction, init));
    }

    @Test
    @DisplayName("equals() : Parameter로 null을 넘겨준 경우 예외 처리 테스트")
    void equals_NullParameter_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.equals(null, finiteIterator));
        assertThrows(IllegalArgumentException.class, () -> Iterators.equals(finiteIterator, null));
        assertThrows(IllegalArgumentException.class, () -> Iterators.equals(null, null));
    }


    @Test
    @DisplayName("equals() : 같은 Iterator를 넘겨준 경우 True를 반환하는지 테스트")
    void equals_SameElementsIterator_True() {
        FiniteIterator<Integer> equalIterator = new FiniteIterator<Integer>() {
            Integer count = 0;

            @Override
            public boolean hasNext() {
                return count < 10;
            }

            @Override
            public Integer next() {
                count = Math.addExact(count, 1);
                return count;
            }
        };

        assertTrue(Iterators.equals(finiteIterator, equalIterator));
    }

    @Test
    @DisplayName("equals() : 다른 Iterator를 넘겨준 경우 False를 반환하는지 테스트")
    void equals_DifferentElementsIterator_False() {

        FiniteIterator<Integer> oddIterator = new FiniteIterator<>() {
            Integer count = 0;
            Integer current = 1;

            @Override
            public boolean hasNext() {
                return count < 10;
            }

            @Override
            public Integer next() {
                count = Math.addExact(count, 1);

                Integer old = current;
                current = Math.addExact(current, 2);
                return old;
            }
        };

        FiniteIterator<Integer> evenIterator = new FiniteIterator<Integer>() {
            Integer count = 0;
            Integer current = 0;

            @Override
            public boolean hasNext() {
                return count < 10;
            }

            @Override
            public Integer next() {
                count = Math.addExact(count, 1);

                Integer old = current;
                current = Math.addExact(current, 2);
                return old;
            }
        };

        assertFalse(Iterators.equals(oddIterator, evenIterator));
    }


    @Test
    @DisplayName("toString() : Parameter로 null이 들어온 경우 예외 처리 테스트")
    void toString_NullParameter_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.toString(null, " "));
        assertThrows(IllegalArgumentException.class, () -> Iterators.toString(finiteIterator, null));
    }

    @Test
    @DisplayName("toString() : FiniteIterator를 넘겨준 경우 정상적으로 String으로 변환되는지 테스트")
    void toString_FiniteIterator_String() {
        String expected = "0, 1, 2, 3, 4";
        FiniteIterator<Integer> iterator = new FiniteIterator<>() {
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < 5;
            }

            @Override
            public Integer next() {
                return count++;
            }
        };

        String separator = ", ";

        assertEquals(expected, Iterators.toString(iterator, separator));
    }

    @Test
    @DisplayName("map() : Parameter로 null이 들어온 경우 예외 처리 테스트")
    void map_NullParameter_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.map(null, x -> x));
        assertThrows(IllegalArgumentException.class, () -> Iterators.map(finiteIterator, null));
    }

    @Test
    @DisplayName("map() : covariant return의 특성을 이용하여 잘못된 타입 캐스팅을 할 경우 예외 처리 테스트")
    void map_CovariantReturnTest_ExceptionThrown() {
        Function<Integer, Integer> function = x -> x * 2;

        // finiteIterator -> InfiniteIterator 강제 형 변환
        assertThrows(ClassCastException.class, () -> {
            InfiniteIterator map = (InfiniteIterator) map(finiteIterator, function);
        });

        // InfiniteIterator -> FiniteIterator 강제 형 변환
        assertThrows(ClassCastException.class, () -> {
            FiniteIterator map = (FiniteIterator) map(infiniteIterator, function);
        });
    }

    @Test
    @DisplayName("map() : FiniteIterator를 넘겨준 경우 정상적으로 FiniteIterator로 Return하는지 테스트")
    void map_NormalCase_FiniteIterator() {
        Function<Integer, Integer> function = x -> x * 2;
        Integer[] testResult = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};

        UndefinedIterator<Integer> finiteMap = Iterators.map(finiteIterator, function);
        assertInstanceOf(FiniteIterator.class, finiteMap);
        int index = 0;
        while (finiteMap.hasNext()) {
            assertEquals(testResult[index++], finiteMap.next());
        }
    }

    @Test
    @DisplayName("map() : InfiniteIterator를 넘겨준 경우 정상적으로 InfiniteIterator로 Return하는지 테스트")
    void map_NormalCase_InfiniteIterator() {
        Function<Integer, Integer> function = x -> x * 2;

        UndefinedIterator<Integer> infiniteMap = Iterators.map(infiniteIterator, function);
        assertInstanceOf(InfiniteIterator.class, infiniteMap);
        assertEquals(2, infiniteMap.next());
        assertEquals(4, infiniteMap.next());
    }


    @Test
    @DisplayName("filter() : Parameter로 null이 들어온 경우 예외 처리 테스트")
    void filter_NullParameter_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.filter(null, x -> true));
        assertThrows(IllegalArgumentException.class, () -> Iterators.filter(finiteIterator, null));
    }

    @Test
    @DisplayName("filter() : 사용자가 넘긴 Iterator에 대해 Predicate를 만족하지 못할 경우 테스트")
    void filter_PredicateError_ExceptionThrown() {

        // finiteIterator = 0 ~ 9까지의 iterator
        UndefinedIterator<Integer> filter = filter(finiteIterator, x -> x > 11);
        assertFalse(filter::hasNext);
        assertThrows(NoSuchElementException.class, filter::next);
    }


    @Test
    @DisplayName("filter() : FiniteIterator를 넘겨준 경우 정상적으로 Predicate을 만족하는 FiniteIterator로 Return하는지 테스트")
    void filter_NormalCase_FiniteToFinite() {

        // filter - FiniteIterator(finite)
        UndefinedIterator<Integer> filter = filter(finiteIterator, x -> x < 5);
        assertInstanceOf(FiniteIterator.class, filter);

        int total = 0;
        while (filter.hasNext()) {
            total += filter.next();
        }

        assertEquals(1 + 2 + 3 + 4, total);
    }

    @Test
    @DisplayName("filter() : InfiniteIterator를 넘겨준 경우 정상적으로 Predicate을 만족하는 FiniteIterator로 Return하는지 테스트")
    void filter_NormalCase_InfiniteToFinite() {
        // filter - InfiniteIterator
        UndefinedIterator<Integer> infiniteFilter = filter(infiniteIterator, x -> x % 2 == 0);
        assertInstanceOf(InfiniteIterator.class, infiniteFilter);

        assertEquals(2, infiniteFilter.next());
        assertEquals(4, infiniteFilter.next());
        assertEquals(6, infiniteFilter.next());
        assertEquals(8, infiniteFilter.next());
        assertEquals(10, infiniteFilter.next());
    }

    @Test
    @DisplayName("findFirst() : Parameter로 null이 들어온 경우 예외 처리 테스트")
    void findFirst_NullParameter_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.findFirst(null, x -> true));
        assertThrows(IllegalArgumentException.class, () -> Iterators.findFirst(finiteIterator, null));
    }

    @Test
    @DisplayName("findFirst() : Iterator에 대해 Predicate를 만족하지 못할 경우 테스트")
    void findFirst_NotSatisfiedPredicate_ExceptionThrown() {
        // NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> Iterators.findFirst(finiteIterator, x -> x > 11));
    }

    @Test
    @DisplayName("findFirst() : Iterator를 넘겨준 경우 정상적으로 Predicate에 만족하는 첫 번째 값을 Return 하는지 테스트")
    void findFirst_NormalCase_ReturnFirstValue() {
        // FiniteIterator가 들어가는 경우
        Integer finiteReturn = Iterators.findFirst(finiteIterator, x -> x % 5 == 0);
        assertEquals(5, finiteReturn);

        // InfiniteIterator가 들어가는 경우
        Integer InfiniteReturn = Iterators.findFirst(infiniteIterator, x -> x % 11 == 0);
        assertEquals(11, InfiniteReturn);
    }

    @Test
    @DisplayName("iterate() : Parameter로 null이 들어온 경우 예외 처리 테스트")
    void iterate_NullParameter_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> iterate(null, x -> true));
        assertThrows(IllegalArgumentException.class, () -> iterate(1, null));
    }


    @Test
    @DisplayName("iterator() : 정상적으로 InfiniteIterator을 Return 하는지 테스트")
    void iterate_NormalCase_ReturnInfiniteIterator() {
        InfiniteIterator<Integer> iterate = Iterators.iterate(0, x -> x + 100);

        assertInstanceOf(InfiniteIterator.class, iterate);

        assertEquals(0, iterate.next());
        assertEquals(100, iterate.next());
        assertEquals(200, iterate.next());
        assertEquals(300, iterate.next());
    }


    @Test
    @DisplayName("limit() : Parameter로 null이 들어온 경우 예외 처리 테스트")
    void limit_NullParameter_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> limit(null, 10));
        assertThrows(IllegalArgumentException.class, () -> limit(infiniteIterator, -1));
    }

    @Test
    @DisplayName("limit() : 사용자가 넘겨준 iterator의 크기보다 maxSize를 크게 넘겨준 경우")
    void limit_MaxSizeBiggerThanIteratorSize_ExceptionThrown() {
        FiniteIterator<Integer> limit = limit(finiteIterator, 11);
        for (int i = 1; i <= 10; i++) {
            assertEquals(i, limit.next());
        }
        assertThrows(IndexOutOfBoundsException.class, limit::next);
    }

    @Test
    @DisplayName("limit() : FiniteIterator를 넘겨준 경우 정상적으로 FiniteIterator로 Return하는지 테스트")
    void limit_NormalCase_FiniteToFinite() {

        FiniteIterator<Integer> finiteLimit = limit(finiteIterator, 3);
        assertInstanceOf(FiniteIterator.class, finiteLimit);

        assertEquals(1, finiteLimit.next());
        assertEquals(2, finiteLimit.next());
        assertEquals(3, finiteLimit.next());
        assertFalse(finiteLimit.hasNext());
    }

    @Test
    @DisplayName("limit() : InfiniteIterator를 넘겨준 경우 정상적으로 FiniteIterator로 Return하는지 테스트")
    void limit_NormalCase_InfiniteToInfinite() {
        FiniteIterator<Integer> infiniteLimit = limit(infiniteIterator, 3);
        assertInstanceOf(FiniteIterator.class, infiniteLimit);

        assertEquals(1, infiniteLimit.next());
        assertEquals(2, infiniteLimit.next());
        assertEquals(3, infiniteLimit.next());
        assertFalse(infiniteLimit.hasNext());
    }

    @Test
    @DisplayName("generate() : Parameter로 null이 들어온 경우 예외 처리 테스트")
    void generate_NullParameter_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.generate(null));
    }

    @Test
    @DisplayName("generate() : InfiniteIterator를 정상적으로 Return 하는지 테스트")
    void generate_NormalCase_ReturnInfiniteIterator() {
        Supplier<Integer> supplier = new Supplier<Integer>() {
            int count = 0;

            @Override
            public Integer get() {
                count = Math.addExact(count, 2);
                return count;
            }
        };

        InfiniteIterator<Integer> infinite = Iterators.generate(supplier);

        assertInstanceOf(InfiniteIterator.class, infinite);
        assertEquals(2, infinite.next());
        assertEquals(4, infinite.next());
        assertEquals(6, infinite.next());
        assertTrue(infinite.hasNext());
    }

    @Test
    @DisplayName("zip() : Parameter로 null이 들어온 경우 예외 처리 테스트")
    void zip_NullParameter_ExceptionThrown() {
        BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> x + y;

        assertThrows(IllegalArgumentException.class, () -> Iterators.zip(null, finiteIterator, finiteIterator));
        assertThrows(IllegalArgumentException.class, () -> Iterators.zip(biFunction, null, finiteIterator));
        assertThrows(IllegalArgumentException.class, () -> Iterators.zip(biFunction, finiteIterator, null));
    }


    @Test
    @DisplayName("zip() : Finite + Finite -> Finite Test")
    void zip_NormalCase_FiniteZipFiniteToFinite() {
        BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> x + y;

        // zip - FiniteIterator(finite)
        FiniteIterator<Integer> FiniteIterator = new FiniteIterator<>() {
            Integer count = 0;
            Integer current = 0;

            @Override
            public boolean hasNext() {
                return count < 10;
            }

            @Override
            public Integer next() {
                count = Math.addExact(count, 1);
                current = Math.addExact(current, 2);
                return current;
            }
        };

        UndefinedIterator<Integer> finiteIteratorZip = Iterators.zip(biFunction, finiteIterator, FiniteIterator);
        assertInstanceOf(FiniteIterator.class, finiteIteratorZip);

        assertEquals(1 + 2, finiteIteratorZip.next());
        assertEquals(2 + 4, finiteIteratorZip.next());
        assertEquals(3 + 6, finiteIteratorZip.next());
    }

    @Test
    @DisplayName("zip() : Finite + Infinite -> Finite Test")
    void zip_NormalCase_FiniteZipInfiniteToFinite() {
        BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> x + y;

        FiniteIterator<Integer> mixNew = new FiniteIterator<Integer>() {
            int count = 0;
            int current = 0;

            @Override
            public boolean hasNext() {
                return count < 3;
            }

            @Override
            public Integer next() {
                current = Math.addExact(current, 1);
                count = Math.addExact(count, 1);
                return current;
            }
        };

        InfiniteIterator<Integer> mixFinite = new InfiniteIterator<Integer>() {
            int current = 0;

            @Override
            public Integer next() {
                current = Math.addExact(current, 1);
                return current;
            }
        };

        UndefinedIterator<Integer> mixIteratorZip = Iterators.zip(biFunction, mixNew, mixFinite);
        assertInstanceOf(FiniteIterator.class, mixIteratorZip);

        assertEquals(1 + 1, mixIteratorZip.next());
        assertEquals(2 + 2, mixIteratorZip.next());
        assertEquals(3 + 3, mixIteratorZip.next());
        assertFalse(mixIteratorZip.hasNext());
    }

    @Test
    @DisplayName("zip() : Infinite + Infinite -> Infinite Test")
    void zip_NormalCase_InfiniteToInfinite() {

        BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> x + y;

        // zip - InfiniteIterator
        InfiniteIterator<Integer> newInfinite = new InfiniteIterator<Integer>() {
            Integer current = 0;

            @Override
            public Integer next() {
                current = Math.addExact(current, 2);
                return current;
            }
        };

        UndefinedIterator<Integer> infiniteIteratorZip = Iterators.zip(biFunction, infiniteIterator, newInfinite);
        assertInstanceOf(InfiniteIterator.class, infiniteIteratorZip);

        assertEquals(1 + 2, infiniteIteratorZip.next());
        assertEquals(2 + 4, infiniteIteratorZip.next());
        assertEquals(3 + 6, infiniteIteratorZip.next());
    }


    @Test
    @DisplayName("count() : Parameter로 null이 들어온 경우 예외 처리 테스트")
    void count_NullParameter_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.count(null));
    }

    @Test
    @DisplayName("count() : FiniteIterator를 넘겨 정상적으로 count 하는지 테스트")
    void count_NormalCase_ReturnFiniteIteratorSize() {
        long total = 10;
        assertEquals(total, Iterators.count(finiteIterator));
    }

    @Test
    @DisplayName("get() : Parameter로 null이 들어온 경우 예외 처리 테스트")
    void get_NullParameter_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.get(null, 10));
    }

    @Test
    @DisplayName("get() : 잘못된 index 값을 넘겨준 경우 예외 처리 테스트")
    void get_InvalidIndex_ExceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class, () -> Iterators.get(finiteIterator, -1));

        // iterator의 사이즈보다 index가 큰 경우
        assertThrows(IndexOutOfBoundsException.class, () -> Iterators.get(finiteIterator, 11));
    }


    @Test
    @DisplayName("get() : FiniteIterator를 넘겨 정상적으로 index에 해당하는 값을 return 하는지 테스트")
    void get_NormalCase_ReturnFiniteIteratorIndexValue() {
        // FiniteIterator -> 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        int index = 9;
        Integer finiteResult = Iterators.get(finiteIterator, index);
        assertEquals(index + 1, finiteResult);

    }

    @Test
    @DisplayName("get() : InfiniteIterator를 넘겨 정상적으로 index에 해당하는 값을 return 하는지 테스트")
    void get_NormalCase_ReturnInfiniteIteratorIndexValue() {

        // index = 0번부터 시작
        // infiniteIterator -> 1, 2, 3, 4, 5 ...

        int index = 5;
        Integer infiniteResult = Iterators.get(infiniteIterator, index);
        assertEquals(index + 1, infiniteResult);
    }

    @Test
    @DisplayName("toList() : Parameter로 null이 들어온 경우 예외 처리 테스트")
    void toList_NullParameter_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> Iterators.toList(null));
    }

    @Test
    @DisplayName("toList() : FiniteIterator를 넘겨 정상적으로 List로 변환하는지 테스트")
    void toList_NormalCase_ReturnToList() {

        // finiteIterator -> 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        List<Integer> list = Iterators.toList(finiteIterator);

        assertEquals(10, list.size());
        assertEquals(1, list.get(0));
        assertEquals(10, list.get(9));
    }
}