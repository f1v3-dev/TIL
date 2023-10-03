package com.nhnacademy.montecarlo;

import java.util.Iterator;
import java.util.Random;

public class MonteCarlo<T extends Number> implements Iterator<Boolean> {

    private static final Random random = new Random();
    private final int iteration;
    private int currentIteration;

    public MonteCarlo(int iteration) {
        this.iteration = iteration;
        this.currentIteration = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIteration <= iteration;
    }

    @Override
    public Boolean next() {


        double x = random.nextDouble();
        double y = random.nextDouble();

        double result = Math.pow(x, 2) + Math.pow(y, 2);

        currentIteration++;
        return result <= 1;
    }

}