package org.example;


import java.util.Objects;
import java.util.function.Consumer;

/**
 * <h3>기존의 Iterator와 InfiniteIterator의 상속관계의 문제로 인해서 발생한 문제를 해결하기 위한 인터페이스</h3>
 *
 * <p>UndefinedIterator의 서브 타입에 InfiniteIterator, FiniteIterator 두개를 존재시키도록 구성<br/>
 * -> 두 인터페이스 모두 들어갈 수 있는 경우 UndefinedIterator를 매개변수로 받는 메서드를 구현하였음.</p>
 * <br/>
 * 이로 인해 LSP 위반 해결 및 불필요한 메서드의 구현을 막을 수 있음. <br/>
 * 하지만, Iterable을 사용할 수 없으므로 while()문을 통해 Iterable이 필요한 경우를 처리하였음.
 */
public interface UndefinedIterator<E> {
    boolean hasNext();

    E next();

    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext()) {
            action.accept(next());
        }
    }
}