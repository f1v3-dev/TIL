package com.nhnacademy.simulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HerbSimulationTest {

    @Test
    @DisplayName("[HerbSimulation] Iterator Test")
    void iteratorTest() {
        int trials = 1_000_000;
        double estimatedEfficacy = HerbSimulationIterator.simulate(trials);

        assertEquals(90.0, estimatedEfficacy, 0.01);
    }

    @Test
    @DisplayName("[HerbSimulation] Stream Test")
    void streamTest() {
        int trials = 1_000_000;
        double estimatedEfficacy = HerbSimulationStream.simulate(trials);

        assertEquals(90.0, estimatedEfficacy, 0.01);
    }

}