import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class MonteCarloStream {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("시도 횟수를 입력하세요: ");
        int totalCycle = sc.nextInt();

        Random random = new Random();

        Stream<Double> stream = Stream.generate(() -> {
            double x = random.nextDouble();
            double y = random.nextDouble();
            double result = Math.pow(x, 2) + Math.pow(y, 2);

            if (result <= 1) {
                return 1.0;
            } else {
                return 0.0;
            }
        });

        stream.limit(totalCycle)
                .mapToDouble(Double::doubleValue)
                .average()
                .ifPresent(average -> System.out.println(average * 4));
    }
}
