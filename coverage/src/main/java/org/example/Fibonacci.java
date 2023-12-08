package org.example;

public class Fibonacci extends InfiniteIterator<Integer> {

    // TODO : Fibonacci
    private Integer first;
    private Integer second;

    public Fibonacci() {
        first = 0;
        second = 1;
    }

    public Integer getFirst() {
        return first;
    }

    public Integer getSecond() {
        return second;
    }

    private void setFirst(Integer first) {
        this.first = first;
    }

    private void setSecond(Integer second) {
        this.second = second;
    }

    @Override
    public Integer next() {
        Integer current = this.getFirst();
        this.setFirst(this.getSecond());
        this.setSecond(Math.addExact(current, this.getSecond()));
        return current;
    }
}
