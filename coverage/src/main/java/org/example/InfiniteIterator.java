package org.example;

/**
 * 만약, interface로 구현한다면, FiniteIterator와 InfiniteIterator 두 개를 모두 상속받는
 * 이상한 관계를 갖는 클래스가 나올 수 있다.
 * 이러한 문제를 해결하기 위해서 interface가 아닌 abstract class로 구현하였습니다.
 */
public abstract class InfiniteIterator<T> implements UndefinedIterator<T> {

    @Override
    public boolean hasNext() {
        return true;
    }

    public abstract T next();
}