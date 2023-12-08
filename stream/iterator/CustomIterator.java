import java.util.Iterator;
import java.util.List;

public class CustomIterator {
    public static void main(String[] args) {
        List<String> listOfStrings = List.of("hello", "world", "this", "is", "a", "test");

        Iterator<String> iterator = listOfStrings.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
