import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Type {
    public static void main(String[] args) {

        System.out.println("[int]");
        IntStream intStream = new Random().ints(4);
        intStream.forEach(System.out::println);

        System.out.println("\n[long]");
        LongStream longStream = new Random().longs(4);
        longStream.forEach(System.out::println);

        System.out.println("\n[double]");
        DoubleStream doubleStream = new Random().doubles(4);
        doubleStream.forEach(System.out::println);

    }
}
