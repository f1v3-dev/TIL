import java.util.stream.IntStream;

public class Range {
    public static void main(String[] args) {
        IntStream stream1 = IntStream.range(1, 4);
        stream1.forEach(x -> System.out.print(x + " "));
        System.out.println();

        IntStream stream2 = IntStream.rangeClosed(1, 4);
        stream2.forEach(x -> System.out.print(x + " "));
    }
}
