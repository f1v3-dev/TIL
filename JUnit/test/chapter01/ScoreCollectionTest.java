package chapter01;

import static org.junit.jupiter.api.Assertions.*;

import chapter01.ScoreCollection;
import org.junit.jupiter.api.Test;

class ScoreCollectionTest {
    @Test
    public void test() {
        // 준비
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        // 실행
        int actualResult = collection.arithmeticMean();

        // 단언
        assertEquals(6, actualResult);
    }
}