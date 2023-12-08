package org.example;

import java.util.NoSuchElementException;

public class Experiments<T extends Number> implements UndefinedIterator<T> {
    // TODO 채우기
    private UndefinedIterator<T> iterator;
    private String title;
    private String distribution;
    private Long count;
    private Long sum;


    public Experiments(UndefinedIterator<T> iterator, String title, String distribution) {
        this.iterator = iterator;
        this.title = title;
        this.distribution = distribution;
        this.count = 0L;
        this.sum = 0L;
    }

    public String getTitle() {
        return title;
    }

    public UndefinedIterator<T> getIterator() {
        return iterator;
    }

    public String getDistribution() {
        return distribution;
    }

    public Long getCount() {
        return count;
    }

    public Long getSum() {
        return sum;
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Experiment");
        }
        T element = this.getIterator().next();
        count = Math.addExact(count, 1);
        sum = Math.addExact(sum, element.longValue());
        return element;
    }

    public void report() {
        System.out.println(getTitle() + " [" + getDistribution() + "] ");
        System.out.println(getSum().doubleValue() / getCount());
    }

}