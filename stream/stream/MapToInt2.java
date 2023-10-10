import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapToInt2 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> listWithStreamMapToInt = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        List<Integer> listWithStreamMap = Stream.of(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println(listWithStreamMapToInt.toString());
        System.out.println(listWithStreamMap.toString());
    }
}
