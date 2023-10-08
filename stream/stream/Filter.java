import java.util.stream.IntStream;

public class Filter {
    public static void main(String[] args) {
        IntStream stream1 = IntStream.of(7, 5, 5, 2, 1, 2, 3, 5, 4, 6);
        IntStream stream2 = IntStream.of(7, 5, 5, 2, 1, 2, 3, 5, 4, 6);

        // 스트림에서 중복된 요소 제거
        stream1.distinct().forEach(x -> System.out.print(x + " "));
        System.out.println();

        // 스트림에서 홀수만 골라내기
        stream2.filter(n -> n % 2 == 0).forEach(x -> System.out.print(x + " "));
    }
}
