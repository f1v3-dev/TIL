
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetIterator {
    public static void main(String[] args) {

        // Collection 생성 -> Set (순서 X)
        Set<String> cars = new HashSet<>();

        cars.add("벤츠");
        cars.add("람보르기니");
        cars.add("롤스로이스");
        cars.add("페라리");

        // Iterator 생성
        System.out.println("[Iterator]");
        Iterator<String> iterator = cars.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


        // for-each
        System.out.println("\n[for-each]");
        for (String car : cars) {
            System.out.println(car);
        }
    }
}
