import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lambda {
    public static void main(String[] args) {
        IntStream stream = IntStream.iterate(2, n -> n + 2);
//        stream.filter(n ->  n > 0 && n < 100).forEach(System.out::println);

    }
}
