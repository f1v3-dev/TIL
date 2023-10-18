package factoryMethodPattern;

public class Cat extends Animal {
    AnimalToy getToy() {
        return new CatToy();
    }
}
