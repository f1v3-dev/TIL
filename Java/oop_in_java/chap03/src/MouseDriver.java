package chap03.src;

public class MouseDriver {
    public static void main(String[] args) {


//        Mouse mickey = new Mouse();
//        mickey.name = "미키";
//        mickey.age = 85;
//        mickey.countOfTail = 1;
//        mickey.sing();
//
//        Mouse jerry = new Mouse();
//        jerry.name = "제리";
//        jerry.age = 73;
//        jerry.countOfTail = 1;
//        jerry.sing();

        Mouse.countOfTail = 1;

        Mouse mickey = new Mouse();
        Mouse jerry = new Mouse();
        Mouse mightyMouse = new Mouse();

        System.out.println(mickey.countOfTail);
        System.out.println(jerry.countOfTail);
        System.out.println(mightyMouse.countOfTail);

        System.out.println(Mouse.countOfTail);
    }
}
