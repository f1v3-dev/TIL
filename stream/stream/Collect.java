import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collect {
    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("넷", "둘", "하나", "셋");

        List<String> list = stream1.collect(Collectors.toList());
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }

        System.out.println();
        Stream<String> stream2 = Stream.of("HTML", "CSS", "JAVA", "PHP");

        Map<Boolean, List<String>> partition = stream2.collect(Collectors.partitioningBy(s -> (s.length() % 2) == 0));

        List<String> oddLengthList = partition.get(false);
        System.out.println(oddLengthList);

        List<String> evenLengthList = partition.get(true);
        System.out.println(evenLengthList);
    }
}
