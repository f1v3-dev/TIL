package com.nhnacademy.montecarlo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MonteCarloTest {

    @Test
    @DisplayName("[Monte Carlo] Iterator Test")
    void iteratorTest() {
        int totalCycle = 10000;
        double estimatedPi = MonteCarloIterator.estimatePi(totalCycle);
        assertEquals(3.14, estimatedPi, 0.01);
    }

    @Test
    @DisplayName("[Monte Carlo] Stream Test")
    void streamTest() {
        int totalCycle = 10000;

        double estimatedPi = MonteCarloStream.estimatedPi(totalCycle);
        assertEquals(3.14, estimatedPi, 0.01);
    }

    @Test
    @DisplayName("[Monte Carlo] Stream Parallel Test")
    void streamParallelTest() {
        int totalCycle = 10_000;

        double piEstimation = MonteCarloStreamParallel.estimatedPi(totalCycle);
        assertEquals(3.14, piEstimation, 0.01);
    }

}