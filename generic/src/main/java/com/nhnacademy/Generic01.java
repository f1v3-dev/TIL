package com.nhnacademy;

import java.util.ArrayList;
import java.util.List;

class LandAnimal {
    public void crying() {
        System.out.println("육지 동물");
    }
}

class Cat extends LandAnimal {
    @Override
    public void crying() {
        System.out.println("냐옹");
    }
}

class Dog extends LandAnimal {
    @Override
    public void crying() {
        System.out.println("멍멍");
    }
}

class Sparrow {
    public void crying() {
        System.out.println("짹짹");
    }
}

class AnimalList<T> {
    List<T> animalList = new ArrayList<>();

    void add(T animal) {
        animalList.add(animal);
    }

    T get(int index) {
        return animalList.get(index);
    }

    boolean remove(T animal) {
        return animalList.remove(animal);
    }

    int size() {
        return animalList.size();
    }

}

public class Generic01 {
    public static void main(String[] args) {
        AnimalList<LandAnimal> landAnimal = new AnimalList<>();

        landAnimal.add(new LandAnimal());
        landAnimal.add(new Cat());
        landAnimal.add(new Dog());

        landAnimal.animalList
                .forEach(LandAnimal::crying);




    }
}
