
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListIterator {
    public static void main(String[] args) {

        // Collection 객체 생성 -> ArrayList
        List<String> cars = new ArrayList<>();

        cars.add("벤츠");
        cars.add("람보르기니");
        cars.add("롤스로이스");
        cars.add("페라리");

        // iterator 획득
        System.out.println("[Iterator]");
        Iterator<String> iterator = cars.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


        // for-each 사용
        System.out.println("\n[for-each]");
        for (String car : cars) {
            System.out.println(car);
        }
    }
}
