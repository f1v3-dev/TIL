package org.example.chapter01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ScoreCollectionTest {

    @Test
    public void answersArithmeticMeanOfTwoNumbers() {

        // 준비
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        // 실행
        int actualResult = collection.arithmeticMean();

        // 단언
        assertThat(actualResult, equalTo(6));
    }

}