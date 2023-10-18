package factoryMethodPattern;

public class Driver {
    public static void main(String[] args) {

        Animal bolt = new Dog();
        Animal kitty = new Cat();

        // Factory Method가 반환하는 객체들
        AnimalToy boltBall = bolt.getToy();
        AnimalToy kittyTower = kitty.getToy();

        // Factory Method가 반환하는 객체들을 사용
        boltBall.identify();
        kittyTower.identify();
    }
}
