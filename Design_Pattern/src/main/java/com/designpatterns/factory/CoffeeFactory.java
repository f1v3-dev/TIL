package com.designpatterns.factory;

public class CoffeeFactory {

//    public static Coffee getCoffee(String type, int price) {
//        if ("Latte".equalsIgnoreCase(type)) {
//            return new Latte(price);
//        } else if ("Americano".equalsIgnoreCase(type)) {
//            return new Americano(price);
//        } else {
//            return new DefaultCoffee();
//        }
//    }

    public static Coffee getCoffee(CoffeeType type, int price) {

        switch (type) {
            case Latte:
                return new Latte(price);

            case Americano:
                return new Americano(price);

            default:
                return new DefaultCoffee();
        }
    }
}
