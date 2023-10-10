import java.util.ArrayList;
import java.util.List;

public class MapToInt {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 100_000_000; i++) {
            list.add(i);
        }

        long startTime = System.currentTimeMillis();

        list.stream()
                .map(x -> Math.pow(x, 2))
                .forEach(x -> {
                });

        long endTime = System.currentTimeMillis();

        System.out.println("map 소요 시간 : " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        list.stream()
                .mapToInt(x -> (int) Math.pow(x, 2))
                .forEach(x -> {
                });
        endTime = System.currentTimeMillis();
        System.out.println("mapToInt 소요 시간 : " + (endTime - startTime) + "ms");
    }
}
