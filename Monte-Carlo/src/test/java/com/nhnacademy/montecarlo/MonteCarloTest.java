package com.nhnacademy.montecarlo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MonteCarloTest {

    @Test
    @DisplayName("[MonteCarlo] Iterator Test")
    void iteratorTest() {
        int totalCycle = 10000;
        double estimatedPi = MonteCarloIterator.estimatePi(totalCycle);
        assertEquals(3.14, estimatedPi, 0.03);
    }

    @Test
    @DisplayName("[MonteCarlo] Stream Test")
    void streamTest() {
        int totalCycle = 10000;

        double estimatedPi = MonteCarloStream.estimatedPi(totalCycle);
        assertEquals(3.14, estimatedPi, 0.03);
    }


}