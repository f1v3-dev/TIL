package com.designpatterns.factory;

public class App {

    public static void main(String[] args) {
        Coffee latte = CoffeeFactory.getCoffee(CoffeeType.Latte, 4000);
        Coffee americano = CoffeeFactory.getCoffee(CoffeeType.Americano, 3000);

        System.out.println("Factory latte = " + latte);
        System.out.println("Factory americano = " + americano);
    }
}
