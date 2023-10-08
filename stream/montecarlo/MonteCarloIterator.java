import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class MonteCarloIterator {

    static int insideCircle = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("시도 횟수를 입력하세요: ");
        int totalCycle = sc.nextInt();

        Random random = new Random();

        Iterator<Double> iterator = new Iterator<Double>() {
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < totalCycle;
            }

            @Override
            public Double next() {
                count++;
                double x = random.nextDouble();
                double y = random.nextDouble();

                double result = Math.pow(x, 2) + Math.pow(y, 2);
                if (result <= 1) {
                    insideCircle++;
                }

                return (double) insideCircle / count * 4;
            }
        };

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
